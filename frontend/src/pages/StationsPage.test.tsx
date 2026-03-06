import {render, screen, waitFor} from '@testing-library/react';
import {App} from 'antd';
import {mockStation, mockStation2} from '../test-utils/mockData';
import {LocationService} from '../services';
import StationsPage from './StationsPage';

vi.mock('../services', () => ({
    LocationService: {
        getAllStations: vi.fn(),
    },
}));

function renderWithApp(ui: React.ReactElement) {
    return render(<App>{ui}</App>);
}

describe('StationsPage', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    it('should render "Станции" title', async () => {
        vi.mocked(LocationService.getAllStations).mockResolvedValue({
            data: {stations: [], totalElements: 0, totalPages: 0, size: 20, number: 0},
        } as never);

        renderWithApp(<StationsPage/>);

        expect(await screen.findByText('Станции')).toBeInTheDocument();
    });

    it('should load and display station list', async () => {
        vi.mocked(LocationService.getAllStations).mockResolvedValue({
            data: {
                stations: [mockStation, mockStation2],
                totalElements: 2,
                totalPages: 1,
                size: 20,
                number: 0,
            },
        } as never);

        renderWithApp(<StationsPage/>);

        await waitFor(() => {
            expect(screen.getByText('Екатеринбург-Пасс.')).toBeInTheDocument();
            expect(screen.getByText('Первомайская')).toBeInTheDocument();
        });
    });

    it('should render "Добавить станцию" button', async () => {
        vi.mocked(LocationService.getAllStations).mockResolvedValue({
            data: {stations: [], totalElements: 0, totalPages: 0, size: 20, number: 0},
        } as never);

        renderWithApp(<StationsPage/>);

        expect(await screen.findByText('Добавить станцию')).toBeInTheDocument();
    });

    it('should show error message on failed fetch', async () => {
        vi.mocked(LocationService.getAllStations).mockRejectedValue(
            new Error('Network error')
        );

        renderWithApp(<StationsPage/>);

        await waitFor(() => {
            expect(LocationService.getAllStations).toHaveBeenCalled();
        });
    });

    it('should call getAllStations on mount', async () => {
        vi.mocked(LocationService.getAllStations).mockResolvedValue({
            data: {stations: [], totalElements: 0, totalPages: 0, size: 20, number: 0},
        } as never);

        renderWithApp(<StationsPage/>);

        await waitFor(() => {
            expect(LocationService.getAllStations).toHaveBeenCalledWith({page: 0, size: 20});
        });
    });
});
