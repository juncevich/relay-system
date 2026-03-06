import {render, screen, waitFor} from '@testing-library/react';
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

    it('should render navigation menu with 3 items', async () => {
        render(<AppLayout/>);

        expect(await screen.findByText('Главная')).toBeInTheDocument();
        expect(await screen.findByText('Реле')).toBeInTheDocument();
        expect(await screen.findByText('Станции')).toBeInTheDocument();
    });

    it('should render footer', async () => {
        render(<AppLayout/>);

        expect(await screen.findByText(/Relay System/)).toBeInTheDocument();
    });

    it('should render Outlet', async () => {
        render(<AppLayout/>);

        expect(await screen.findByTestId('outlet')).toBeInTheDocument();
    });

    it('should highlight "Главная" menu item for root path', async () => {
        mockPathname = '/';
        render(<AppLayout/>);

        await waitFor(() => {
            const menuItem = screen.getByText('Главная').closest('li');
            expect(menuItem).toHaveClass('ant-menu-item-selected');
        });
    });

    it('should highlight "Реле" menu item for /relays path', async () => {
        mockPathname = '/relays';
        render(<AppLayout/>);

        await waitFor(() => {
            const menuItem = screen.getByText('Реле').closest('li');
            expect(menuItem).toHaveClass('ant-menu-item-selected');
        });
    });

    it('should highlight "Станции" menu item for /stations path', async () => {
        mockPathname = '/stations';
        render(<AppLayout/>);

        await waitFor(() => {
            const menuItem = screen.getByText('Станции').closest('li');
            expect(menuItem).toHaveClass('ant-menu-item-selected');
        });
    });
});
