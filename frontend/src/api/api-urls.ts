const baseUrl = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8082';
const apiPrefix = import.meta.env.VITE_API_PREFIX ?? '/api/v1';

export const RELAY_STORE_SERVICE_URL = `${baseUrl}${apiPrefix}`;
