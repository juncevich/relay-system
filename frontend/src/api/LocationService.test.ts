import type {Mocked} from 'vitest';
import LocationService from './LocationService';
import {relayStoreHttp} from './http-common';

vi.mock('./http-common');

const mockHttp = relayStoreHttp as Mocked<typeof relayStoreHttp>;

describe('LocationService', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    // ========== Stations ==========

    describe('getAllStations', () => {
        it('should fetch all stations with default pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await LocationService.getAllStations();

            expect(mockHttp.get).toHaveBeenCalledWith('/stations', {
                params: { page: 0, size: 10 }
            });
        });

        it('should fetch all stations with custom pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await LocationService.getAllStations({ page: 1, size: 20 });

            expect(mockHttp.get).toHaveBeenCalledWith('/stations', {
                params: { page: 1, size: 20 }
            });
        });
    });

    describe('getStationById', () => {
        it('should fetch station by id', async () => {
            const mockStation = { id: 1, name: 'Test Station' };
            mockHttp.get.mockResolvedValue({ data: mockStation });

            await LocationService.getStationById(1);

            expect(mockHttp.get).toHaveBeenCalledWith('/stations/1');
        });
    });

    describe('createStation', () => {
        it('should create a new station', async () => {
            const stationName = 'New Station';
            const mockStation = { id: 1, name: stationName };
            mockHttp.post.mockResolvedValue({ data: mockStation });

            await LocationService.createStation(stationName);

            expect(mockHttp.post).toHaveBeenCalledWith('/stations', { name: stationName });
        });
    });

    describe('updateStation', () => {
        it('should update an existing station', async () => {
            const newName = 'Updated Station';
            const mockStation = { id: 1, name: newName };
            mockHttp.put.mockResolvedValue({ data: mockStation });

            await LocationService.updateStation(1, newName);

            expect(mockHttp.put).toHaveBeenCalledWith('/stations/1', { name: newName });
        });
    });

    describe('deleteStation', () => {
        it('should delete a station', async () => {
            mockHttp.delete.mockResolvedValue({});

            await LocationService.deleteStation(1);

            expect(mockHttp.delete).toHaveBeenCalledWith('/stations/1');
        });
    });

    // ========== Track Points ==========

    describe('getAllTrackPoints', () => {
        it('should fetch all track points with default pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await LocationService.getAllTrackPoints();

            expect(mockHttp.get).toHaveBeenCalledWith('/track-points', {
                params: { page: 0, size: 10 }
            });
        });

        it('should fetch all track points with custom pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await LocationService.getAllTrackPoints({ page: 2, size: 15 });

            expect(mockHttp.get).toHaveBeenCalledWith('/track-points', {
                params: { page: 2, size: 15 }
            });
        });
    });

    describe('getTrackPointById', () => {
        it('should fetch track point by id', async () => {
            const mockTrackPoint = { id: 1, name: 'Track Point 1' };
            mockHttp.get.mockResolvedValue({ data: mockTrackPoint });

            await LocationService.getTrackPointById(1);

            expect(mockHttp.get).toHaveBeenCalledWith('/track-points/1');
        });
    });

    describe('createTrackPoint', () => {
        it('should create a new track point', async () => {
            const trackPointName = 'New Track Point';
            const mockTrackPoint = { id: 1, name: trackPointName };
            mockHttp.post.mockResolvedValue({ data: mockTrackPoint });

            await LocationService.createTrackPoint(trackPointName);

            expect(mockHttp.post).toHaveBeenCalledWith('/track-points', { name: trackPointName });
        });
    });

    describe('updateTrackPoint', () => {
        it('should update an existing track point', async () => {
            const newName = 'Updated Track Point';
            const mockTrackPoint = { id: 1, name: newName };
            mockHttp.put.mockResolvedValue({ data: mockTrackPoint });

            await LocationService.updateTrackPoint(1, newName);

            expect(mockHttp.put).toHaveBeenCalledWith('/track-points/1', { name: newName });
        });
    });

    describe('deleteTrackPoint', () => {
        it('should delete a track point', async () => {
            mockHttp.delete.mockResolvedValue({});

            await LocationService.deleteTrackPoint(1);

            expect(mockHttp.delete).toHaveBeenCalledWith('/track-points/1');
        });
    });

    // ========== Crossings ==========

    describe('getAllCrossings', () => {
        it('should fetch all crossings with default pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await LocationService.getAllCrossings();

            expect(mockHttp.get).toHaveBeenCalledWith('/crossings', {
                params: { page: 0, size: 10 }
            });
        });

        it('should fetch all crossings with custom pagination', async () => {
            const mockResponse = { data: { content: [], totalElements: 0 } };
            mockHttp.get.mockResolvedValue(mockResponse);

            await LocationService.getAllCrossings({ page: 3, size: 25 });

            expect(mockHttp.get).toHaveBeenCalledWith('/crossings', {
                params: { page: 3, size: 25 }
            });
        });
    });

    describe('getCrossingById', () => {
        it('should fetch crossing by id', async () => {
            const mockCrossing = { id: 1, name: 'Crossing 1' };
            mockHttp.get.mockResolvedValue({ data: mockCrossing });

            await LocationService.getCrossingById(1);

            expect(mockHttp.get).toHaveBeenCalledWith('/crossings/1');
        });
    });

    describe('createCrossing', () => {
        it('should create a new crossing', async () => {
            const crossingName = 'New Crossing';
            const mockCrossing = { id: 1, name: crossingName };
            mockHttp.post.mockResolvedValue({ data: mockCrossing });

            await LocationService.createCrossing(crossingName);

            expect(mockHttp.post).toHaveBeenCalledWith('/crossings', { name: crossingName });
        });
    });

    describe('updateCrossing', () => {
        it('should update an existing crossing', async () => {
            const newName = 'Updated Crossing';
            const mockCrossing = { id: 1, name: newName };
            mockHttp.put.mockResolvedValue({ data: mockCrossing });

            await LocationService.updateCrossing(1, newName);

            expect(mockHttp.put).toHaveBeenCalledWith('/crossings/1', { name: newName });
        });
    });

    describe('deleteCrossing', () => {
        it('should delete a crossing', async () => {
            mockHttp.delete.mockResolvedValue({});

            await LocationService.deleteCrossing(1);

            expect(mockHttp.delete).toHaveBeenCalledWith('/crossings/1');
        });
    });

    // ========== Error Handling ==========

    describe('error handling', () => {
        it('should propagate errors from failed requests', async () => {
            const error = new Error('Network error');
            mockHttp.get.mockRejectedValue(error);

            await expect(LocationService.getAllStations()).rejects.toThrow('Network error');
            await expect(LocationService.getAllTrackPoints()).rejects.toThrow('Network error');
            await expect(LocationService.getAllCrossings()).rejects.toThrow('Network error');
        });
    });
});
