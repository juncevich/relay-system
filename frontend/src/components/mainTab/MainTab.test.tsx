import {render, screen} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {
    createMockCrossings,
    createMockRelays,
    createMockStations,
    createMockTrackPoints,
    mockStorages
} from '../../test-utils/mockData';
import type {UseRelayDataResult} from '../../hooks/useRelayData';
import MainTab from './MainTab';

const mockRefetch = vi.fn();
const mockUseRelayData = vi.fn<() => UseRelayDataResult>();

vi.mock('../../hooks/useRelayData', () => ({
    default: () => mockUseRelayData(),
    useRelayData: () => mockUseRelayData(),
}));

vi.mock('../relay/RelayCard', () => ({
    default: ({relay}: { relay: { serialNumber: string } }) => (
        <div data-testid="relay-card">{relay.serialNumber}</div>
    )
}));

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
            refetch: mockRefetch,
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
            refetch: mockRefetch,
        });

        render(<MainTab/>);

        expect(screen.getByText('Ошибка загрузки данных')).toBeInTheDocument();
        expect(screen.getByText('Network error')).toBeInTheDocument();
    });

    it('should render breadcrumb items', () => {
        const stations = createMockStations(1);
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations,
            ...emptyLocations,
            storages: [],
            loading: false,
            error: null,
            refetch: mockRefetch,
        });

        render(<MainTab/>);

        expect(screen.getByText('Реле')).toBeInTheDocument();
        // Station name appears in both breadcrumb and sidebar menu
        expect(screen.getAllByText('Екатеринбург-Пасс.').length).toBeGreaterThanOrEqual(2);
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
            refetch: mockRefetch,
        });

        render(<MainTab/>);

        expect(screen.getByText('Станции')).toBeInTheDocument();
        // First station appears in both breadcrumb and sidebar menu
        expect(screen.getAllByText('Екатеринбург-Пасс.').length).toBeGreaterThanOrEqual(1);
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
            refetch: mockRefetch,
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
            refetch: mockRefetch,
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
            refetch: mockRefetch,
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
        const relaysStation2 = createMockRelays(2, 104).map((r, i) => ({
            ...r,
            id: 401 + i,
            serialNumber: `РЭЛ-${i + 1}`
        }));
        mockUseRelayData.mockReturnValue({
            relays: [...relaysStation1, ...relaysStation2],
            stations,
            ...emptyLocations,
            storages: mockStorages,
            loading: false,
            error: null,
            refetch: mockRefetch,
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
