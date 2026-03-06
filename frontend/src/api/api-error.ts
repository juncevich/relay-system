import {AxiosError} from 'axios';

export interface NormalizedApiError {
    message: string;
    status?: number;
    correlationId?: string;
    details?: unknown;
}

export function normalizeApiError(error: unknown): NormalizedApiError {
    if (error instanceof AxiosError) {
        const responseData = error.response?.data as {message?: string; error?: string} | undefined;
        return {
            message: responseData?.message || responseData?.error || error.message || 'API request failed',
            status: error.response?.status,
            correlationId: error.response?.headers?.['x-correlation-id'],
            details: error.response?.data
        };
    }

    if (error instanceof Error) {
        return {message: error.message};
    }

    return {message: 'Unknown API error'};
}
