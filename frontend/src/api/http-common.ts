import axios from "axios";
import {RELAY_STORE_SERVICE_URL} from "./api-urls";

export const relayStoreHttp = axios.create({
    baseURL: RELAY_STORE_SERVICE_URL,
    headers: {
        "Content-type": "application/json"
    }
});
