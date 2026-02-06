import RelayService from './RelayService';
import {relayStoreHttp} from './http-common';

jest.mock('./http-common');

const mockHttp = relayStoreHttp as jest.Mocked<typeof relayStoreHttp>;

describe('RelayService', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    describe('getAll', () => {
        it('should fetch all relays with default pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await RelayService.getAll();

            expect(mockHttp.get).toHaveBeenCalledWith('/relays', {
                params: { page: 0, size: 10 }
            });
        });

        it('should fetch all relays with custom pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await RelayService.getAll({ page: 2, size: 20 });

            expect(mockHttp.get).toHaveBeenCalledWith('/relays', {
                params: { page: 2, size: 20 }
            });
        });
    });

    describe('getById', () => {
        it('should fetch relay by id', async () => {
            const mockRelay = { id: 1, serialNumber: 'REL-001' };
            mockHttp.get.mockResolvedValue({ data: mockRelay });

            await RelayService.getById(1);

            expect(mockHttp.get).toHaveBeenCalledWith('/relays/1');
        });
    });

    describe('getBySerialNumber', () => {
        it('should fetch relay by serial number', async () => {
            const mockRelay = { id: 1, serialNumber: 'REL-001' };
            mockHttp.get.mockResolvedValue({ data: mockRelay });

            await RelayService.getBySerialNumber('REL-001');

            expect(mockHttp.get).toHaveBeenCalledWith('/relays/serial-number/REL-001');
        });
    });

    describe('getByCreationDate', () => {
        it('should fetch relays by creation date with default pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await RelayService.getByCreationDate('2024-01-15');

            expect(mockHttp.get).toHaveBeenCalledWith('/relays/by-creation-date', {
                params: {
                    date: '2024-01-15',
                    page: 0,
                    size: 10
                }
            });
        });

        it('should fetch relays by creation date with custom pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await RelayService.getByCreationDate('2024-01-15', { page: 1, size: 5 });

            expect(mockHttp.get).toHaveBeenCalledWith('/relays/by-creation-date', {
                params: {
                    date: '2024-01-15',
                    page: 1,
                    size: 5
                }
            });
        });
    });

    describe('getByLastCheckDate', () => {
        it('should fetch relays by last check date with default pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await RelayService.getByLastCheckDate('2024-02-01');

            expect(mockHttp.get).toHaveBeenCalledWith('/relays/by-last-check-date', {
                params: {
                    date: '2024-02-01',
                    page: 0,
                    size: 10
                }
            });
        });

        it('should fetch relays by last check date with custom pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await RelayService.getByLastCheckDate('2024-02-01', { page: 2, size: 15 });

            expect(mockHttp.get).toHaveBeenCalledWith('/relays/by-last-check-date', {
                params: {
                    date: '2024-02-01',
                    page: 2,
                    size: 15
                }
            });
        });
    });

    describe('create', () => {
        it('should create a new relay', async () => {
            const createRequest = {
                serialNumber: 'REL-001',
                dateOfManufacture: '2024-01-15',
                storageId: 1
            };
            const mockResponse = { data: createRequest };
            mockHttp.post.mockResolvedValue(mockResponse);

            await RelayService.create(createRequest);

            expect(mockHttp.post).toHaveBeenCalledWith('/relays', createRequest);
        });
    });

    describe('update', () => {
        it('should update an existing relay', async () => {
            const updateRequest = {
                serialNumber: 'REL-001-UPDATED',
                verificationDate: '2024-02-01'
            };
            const mockRelay = { id: 1, ...updateRequest };
            mockHttp.put.mockResolvedValue({ data: mockRelay });

            await RelayService.update(1, updateRequest);

            expect(mockHttp.put).toHaveBeenCalledWith('/relays/1', updateRequest);
        });
    });

    describe('delete', () => {
        it('should delete a relay', async () => {
            mockHttp.delete.mockResolvedValue({});

            await RelayService.delete(1);

            expect(mockHttp.delete).toHaveBeenCalledWith('/relays/1');
        });
    });

    describe('error handling', () => {
        it('should propagate errors from failed requests', async () => {
            const error = new Error('Network error');
            mockHttp.get.mockRejectedValue(error);

            await expect(RelayService.getAll()).rejects.toThrow('Network error');
        });
    });
});
