import {StorageInfo} from '../api/StorageService';

const mockStorages: StorageInfo[] = [
    {id: 101, name: 'Склад ШЧ Екатеринбург', locationId: 1},
    {id: 102, name: 'Склад ШЧ Реж', locationId: 4},
    {id: 103, name: 'Склад ШЧ Алапаевск', locationId: 6},
    {id: 104, name: 'Релейный шкаф ст. Первомайская', locationId: 2},
    {id: 105, name: 'Релейный шкаф ст. Монетная', locationId: 3},
    {id: 106, name: 'Релейный шкаф ст. Егоршино', locationId: 5},
    {id: 107, name: 'Релейный шкаф переезда 39 км', locationId: 11},
    {id: 108, name: 'Релейный шкаф переезда 85 км', locationId: 12},
];

class MockStorageService {
    async getAllStorages(): Promise<StorageInfo[]> {
        return [...mockStorages];
    }
}

const mockStorageServiceInstance = new MockStorageService();
export default mockStorageServiceInstance;
