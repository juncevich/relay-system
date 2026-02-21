import RealRelayService from '../api/RelayService';
import RealLocationService from '../api/LocationService';
import RealStorageService from '../api/StorageService';
import MockRelayService from '../mock-data/MockRelayService';
import MockLocationService from '../mock-data/MockLocationService';
import MockStorageService from '../mock-data/MockStorageService';

const useMockData = import.meta.env.VITE_USE_MOCK_DATA === 'true';

export const RelayService = useMockData ? MockRelayService : RealRelayService;
export const LocationService = useMockData ? MockLocationService : RealLocationService;
export const StorageService = useMockData ? MockStorageService : RealStorageService;
