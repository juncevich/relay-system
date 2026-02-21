import axios from "axios";
import {RELAY_STORE_SERVICE_URL} from "./api-urls";

export const relayStoreHttp = axios.create({
    baseURL: RELAY_STORE_SERVICE_URL,
    headers: {
        "Content-type": "application/json"
    }
});

relayStoreHttp.interceptors.response.use(
    (response) => response,
    (error) => {
        if (import.meta.env.DEV) {
            console.error(
                '[API Error]',
                error.config?.method?.toUpperCase(),
                error.config?.url,
                error.response?.status,
                error.response?.data
            );
        }
        return Promise.reject(error);
    }
);
