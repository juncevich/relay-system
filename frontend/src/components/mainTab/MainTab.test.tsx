import {render, screen} from '@testing-library/react';
import {createMockRelays, createMockStations} from '../../test-utils/mockData';
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

describe('MainTab', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    it('should show loading spinner when loading', () => {
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations: [],
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
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Home')).toBeInTheDocument();
        expect(screen.getByText('List')).toBeInTheDocument();
        expect(screen.getByText('App')).toBeInTheDocument();
    });

    it('should render tabs with relay counts', () => {
        const relays = createMockRelays(30);
        mockUseRelayData.mockReturnValue({
            relays,
            stations: [],
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Склад 1 (24)')).toBeInTheDocument();
        expect(screen.getByText('Склад 2 (6)')).toBeInTheDocument();
        expect(screen.getByText('Склад 3 (0)')).toBeInTheDocument();
    });

    it('should render station menu items in sidebar', () => {
        const stations = createMockStations(3);
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations,
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Станции')).toBeInTheDocument();
        expect(screen.getByText('Екатеринбург-Пасс.')).toBeInTheDocument();
        expect(screen.getByText('Первомайская')).toBeInTheDocument();
        expect(screen.getByText('Монетная')).toBeInTheDocument();
    });

    it('should render relay cards in grid', () => {
        const relays = createMockRelays(3);
        mockUseRelayData.mockReturnValue({
            relays,
            stations: [],
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        const cards = screen.getAllByTestId('relay-card');
        expect(cards).toHaveLength(3);
        expect(screen.getByText('НМШ-001')).toBeInTheDocument();
        expect(screen.getByText('НМШ-002')).toBeInTheDocument();
        expect(screen.getByText('НМШ-003')).toBeInTheDocument();
    });

    it('should show "Нет реле для отображения" when relay list is empty', () => {
        mockUseRelayData.mockReturnValue({
            relays: [],
            stations: [],
            loading: false,
            error: null,
        });

        render(<MainTab/>);

        expect(screen.getByText('Нет реле для отображения')).toBeInTheDocument();
    });
});
