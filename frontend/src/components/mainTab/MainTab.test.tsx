import {render, screen} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {createMockRelays, createMockStations, createMockTrackPoints, createMockCrossings, mockStorages} from '../../test-utils/mockData';
import type {RelayDataState} from '../../hooks/useRelayData';

const mockUseRelayData = vi.fn<() => RelayDataState>();

vi.mock('../../hooks/useRelayData', () => ({
    default: () => mockUseRelayData(),
    useRelayData: () => mockUseRelayData(),
}));

vi.mock('../relay/RelayCard', () => ({
    default: ({relay}: { relay: { title: string } }) => (
        <div data-testid="relay-card">{relay.title}</div>
    )
}));

import MainTab from './MainTab';

const emptyLocations = { trackPoints: [], crossings: [] };

describe('MainTab', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    it('should show loading spinner when loading', () => {
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations: [],
            ...emptyLocations,
            storages: [],
            loading: true,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Загрузка реле...')).toBeInTheDocument();
    });

    it('should show error alert when error occurs', () => {
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations: [],
            ...emptyLocations,
            storages: [],
            loading: false,
            error: 'Network error',
        });

        render(<MainTab/>);

        expect(screen.getByText('Ошибка загрузки данных')).toBeInTheDocument();
        expect(screen.getByText('Network error')).toBeInTheDocument();
    });

    it('should render breadcrumb items', () => {
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations: [],
            ...emptyLocations,
            storages: [],
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Home')).toBeInTheDocument();
        expect(screen.getByText('List')).toBeInTheDocument();
        expect(screen.getByText('App')).toBeInTheDocument();
    });

    it('should render location menu items in sidebar', () => {
        const stations = createMockStations(3);
        const trackPoints = createMockTrackPoints(2);
        const crossings = createMockCrossings(1);
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations,
            trackPoints,
            crossings,
            storages: [],
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Станции')).toBeInTheDocument();
        expect(screen.getByText('Екатеринбург-Пасс.')).toBeInTheDocument();
        expect(screen.getByText('Первомайская')).toBeInTheDocument();
        expect(screen.getByText('Монетная')).toBeInTheDocument();
        expect(screen.getByText('Перегоны')).toBeInTheDocument();
        expect(screen.getByText('Шарташ')).toBeInTheDocument();
        expect(screen.getByText('Переезды')).toBeInTheDocument();
        expect(screen.getByText('Переезд 39 км')).toBeInTheDocument();
    });

    it('should render relay cards filtered by selected station', () => {
        const stations = createMockStations(2);
        const relays = createMockRelays(3, 101);
        mockUseRelayData.mockReturnValue({
            relays,
            stations,
            ...emptyLocations,
            storages: mockStorages,
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        // First station (id=1) is auto-selected, storage 101 has locationId=1
        const cards = screen.getAllByTestId('relay-card');
        expect(cards).toHaveLength(3);
    });

    it('should show storage name in tab when station has storages', () => {
        const stations = createMockStations(2);
        const relays = createMockRelays(3, 101);
        mockUseRelayData.mockReturnValue({
            relays,
            stations,
            ...emptyLocations,
            storages: mockStorages,
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Склад ШЧ Екатеринбург (3)')).toBeInTheDocument();
    });

    it('should show "Нет реле для отображения" when no relays match selected station', () => {
        const stations = createMockStations(2);
        // Relays with storageId=104 (locationId=2 = Первомайская)
        const relays = createMockRelays(3, 104);
        mockUseRelayData.mockReturnValue({
            relays,
            stations,
            ...emptyLocations,
            storages: mockStorages,
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        // First station (id=1, Екатеринбург) is auto-selected, but relays are at station 2
        // Storage 101 exists at station 1 but has 0 relays
        expect(screen.getByText('Склад ШЧ Екатеринбург (0)')).toBeInTheDocument();
    });

    it('should filter relays when clicking a different station', async () => {
        const stations = createMockStations(2);
        // 3 relays at storage 101 (station 1) + 2 relays at storage 104 (station 2)
        const relaysStation1 = createMockRelays(3, 101);
        const relaysStation2 = createMockRelays(2, 104).map((r, i) => ({...r, id: 401 + i, title: `РЭЛ-${i + 1}`}));
        mockUseRelayData.mockReturnValue({
            relays: [...relaysStation1, ...relaysStation2],
            stations,
            ...emptyLocations,
            storages: mockStorages,
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        // Initially station 1 is selected, showing 3 relays
        expect(screen.getAllByTestId('relay-card')).toHaveLength(3);

        // Click on station 2 (Первомайская)
        const user = userEvent.setup();
        await user.click(screen.getByText('Первомайская'));

        // Now should show 2 relays from station 2
        expect(screen.getAllByTestId('relay-card')).toHaveLength(2);
    });
});
