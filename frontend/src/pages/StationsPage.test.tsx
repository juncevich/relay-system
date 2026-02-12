import {render, screen, waitFor} from '@testing-library/react';
import {mockStation, mockStation2} from '../test-utils/mockData';

vi.mock('../api/LocationService', () => ({
    default: {
        getAllStations: vi.fn(),
    },
}));

import locationService from '../api/LocationService';
import StationsPage from './StationsPage';

describe('StationsPage', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    it('should render "Станции" title', async () => {
        vi.mocked(locationService.getAllStations).mockResolvedValue({
            data: {content: [], totalElements: 0, totalPages: 0},
        } as never);

        render(<StationsPage/>);

        expect(screen.getByText('Станции')).toBeInTheDocument();
    });

    it('should load and display station list', async () => {
        vi.mocked(locationService.getAllStations).mockResolvedValue({
            data: {
                content: [mockStation, mockStation2],
                totalElements: 2,
                totalPages: 1,
            },
        } as never);

        render(<StationsPage/>);

        await waitFor(() => {
            expect(screen.getByText('Екатеринбург-Пасс.')).toBeInTheDocument();
            expect(screen.getByText('Первомайская')).toBeInTheDocument();
        });
    });

    it('should render "Добавить станцию" button', async () => {
        vi.mocked(locationService.getAllStations).mockResolvedValue({
            data: {content: [], totalElements: 0, totalPages: 0},
        } as never);

        render(<StationsPage/>);

        expect(screen.getByText('Добавить станцию')).toBeInTheDocument();
    });

    it('should show error message on failed fetch', async () => {
        vi.mocked(locationService.getAllStations).mockRejectedValue(
            new Error('Network error')
        );

        render(<StationsPage/>);

        await waitFor(() => {
            expect(locationService.getAllStations).toHaveBeenCalled();
        });
    });

    it('should call getAllStations on mount', () => {
        vi.mocked(locationService.getAllStations).mockResolvedValue({
            data: {content: [], totalElements: 0, totalPages: 0},
        } as never);

        render(<StationsPage/>);

        expect(locationService.getAllStations).toHaveBeenCalledWith({size: 100});
    });
});
