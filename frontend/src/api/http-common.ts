import axios, {AxiosError, InternalAxiosRequestConfig} from 'axios';
import {RELAY_STORE_SERVICE_URL} from './api-urls';
import {normalizeApiError} from './api-error';

type RetryableConfig = InternalAxiosRequestConfig & {
    _retryAttempt?: number;
};

const MAX_RETRIES = 2;
const RETRY_BASE_DELAY_MS = 250;

const shouldRetry = (error: AxiosError, attempt: number): boolean => {
    if (attempt >= MAX_RETRIES) {
        return false;
    }
    if (!error.response) {
        return true;
    }
    return error.response.status >= 500;
};

const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

export const relayStoreHttp = axios.create({
    baseURL: RELAY_STORE_SERVICE_URL,
    timeout: 10_000,
    headers: {
        "Content-type": "application/json",
        "Accept": "application/json"
    }
});

relayStoreHttp.interceptors.response.use(
    (response) => response,
    async (error: AxiosError) => {
        const config = error.config as RetryableConfig | undefined;
        const attempt = config?._retryAttempt ?? 0;

        if (config && shouldRetry(error, attempt)) {
            config._retryAttempt = attempt + 1;
            await sleep(RETRY_BASE_DELAY_MS * Math.pow(2, attempt));
            return relayStoreHttp(config);
        }

        const normalizedError = normalizeApiError(error);
        if (import.meta.env.DEV) {
            console.error(
                '[API Error]',
                error.config?.method?.toUpperCase(),
                error.config?.url,
                normalizedError.status,
                normalizedError.message,
                normalizedError.correlationId
            );
        }
        return Promise.reject(normalizedError);
    }
);
