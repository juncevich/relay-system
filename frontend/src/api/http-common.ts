import axios from "axios";
import {DEFAULT_BASE_URL, RELAY_STORE_SERVICE_URL} from "./api-urls";

const http = axios.create({
    baseURL: DEFAULT_BASE_URL,
    headers: {
        "Content-type": "application/json"
    }
});

export const relayStoreHttp = axios.create({
    baseURL: RELAY_STORE_SERVICE_URL,
    headers: {
        "Content-type": "application/json"
    }
});

export default http;
