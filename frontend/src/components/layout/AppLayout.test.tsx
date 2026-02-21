import {render, screen} from '@testing-library/react';
import AppLayout from './AppLayout';

const mockNavigate = vi.fn();
let mockPathname = '/';

vi.mock('react-router', () => ({
    useNavigate: () => mockNavigate,
    useLocation: () => ({pathname: mockPathname}),
    Outlet: () => <div data-testid="outlet">Outlet Content</div>,
}));

describe('AppLayout', () => {
    beforeEach(() => {
        vi.clearAllMocks();
        mockPathname = '/';
    });

    it('should render navigation menu with 3 items', () => {
        render(<AppLayout/>);

        expect(screen.getByText('Главная')).toBeInTheDocument();
        expect(screen.getByText('Реле')).toBeInTheDocument();
        expect(screen.getByText('Станции')).toBeInTheDocument();
    });

    it('should render footer', () => {
        render(<AppLayout/>);

        expect(screen.getByText(/Relay System/)).toBeInTheDocument();
    });

    it('should render Outlet', () => {
        render(<AppLayout/>);

        expect(screen.getByTestId('outlet')).toBeInTheDocument();
    });

    it('should highlight "Главная" menu item for root path', () => {
        mockPathname = '/';
        render(<AppLayout/>);

        const menuItem = screen.getByText('Главная').closest('li');
        expect(menuItem).toHaveClass('ant-menu-item-selected');
    });

    it('should highlight "Реле" menu item for /relays path', () => {
        mockPathname = '/relays';
        render(<AppLayout/>);

        const menuItem = screen.getByText('Реле').closest('li');
        expect(menuItem).toHaveClass('ant-menu-item-selected');
    });

    it('should highlight "Станции" menu item for /stations path', () => {
        mockPathname = '/stations';
        render(<AppLayout/>);

        const menuItem = screen.getByText('Станции').closest('li');
        expect(menuItem).toHaveClass('ant-menu-item-selected');
    });
});
