import {render, screen} from '@testing-library/react';
import {MemoryRouter} from 'react-router';
import HomePage from './HomePage';

describe('HomePage', () => {
    const renderWithRouter = () =>
        render(<MemoryRouter><HomePage/></MemoryRouter>);

    it('should render the page title', () => {
        renderWithRouter();
        expect(screen.getByRole('heading', {level: 1})).toBeInTheDocument();
    });

    it('should render navigation links to relays and stations', () => {
        renderWithRouter();
        // Use exact label to avoid matching "Управление реле"
        expect(screen.getByRole('link', {name: 'Реле'})).toBeInTheDocument();
        expect(screen.getAllByRole('link', {name: /станции/i}).length).toBeGreaterThan(0);
    });

    it('should render stats section', () => {
        renderWithRouter();
        expect(screen.getByText('Всего реле')).toBeInTheDocument();
        expect(screen.getByText('Проверено')).toBeInTheDocument();
        expect(screen.getByText('Просрочено')).toBeInTheDocument();
    });

    it('should render quick-link cards', () => {
        renderWithRouter();
        expect(screen.getByText('Управление реле')).toBeInTheDocument();
        // "Станции" appears both as button link text and as card title — check at least one
        expect(screen.getAllByText('Станции').length).toBeGreaterThan(0);
    });
});
