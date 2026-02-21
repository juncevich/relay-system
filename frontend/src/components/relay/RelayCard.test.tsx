import {render, screen} from '@testing-library/react';
import RelayCard from './RelayCard';
import {mockBackendRelay} from '../../test-utils/mockData';

describe('RelayCard Component', () => {
    it('should render relay card with all data', () => {
        render(<RelayCard relay={mockBackendRelay}/>);

        expect(screen.getByAltText('Реле НМШ-001')).toBeInTheDocument();
        expect(screen.getByText('НМШ-001')).toBeInTheDocument();
        expect(screen.getByText('Дата проверки')).toBeInTheDocument();
        expect(screen.getByText('03.03.2024')).toBeInTheDocument();
    });

    it('should render image with default src', () => {
        render(<RelayCard relay={mockBackendRelay}/>);

        const image = screen.getByAltText('Реле НМШ-001') as HTMLImageElement;
        expect(image.src).toContain('/images/relay-default.svg');
    });

    it('should render all action buttons with correct aria-labels', () => {
        render(<RelayCard relay={mockBackendRelay}/>);

        expect(screen.getByLabelText('Настройки')).toBeInTheDocument();
        expect(screen.getByLabelText('Редактировать')).toBeInTheDocument();
        expect(screen.getByLabelText('Дополнительные действия')).toBeInTheDocument();
    });

    it('should render status icon with correct aria-label', () => {
        render(<RelayCard relay={mockBackendRelay}/>);

        expect(screen.getByLabelText('Статус реле')).toBeInTheDocument();
    });

    it('should handle undefined relay prop', () => {
        render(<RelayCard />);

        expect(screen.getByAltText('Изображение реле')).toBeInTheDocument();
        expect(screen.getByText('Дата проверки')).toBeInTheDocument();
    });

    it('should render with correct CSS class', () => {
        const {container} = render(<RelayCard relay={mockBackendRelay}/>);

        expect(container.querySelector('.relay-card-container')).toBeInTheDocument();
    });

    it('should display checking date in table format', () => {
        render(<RelayCard relay={mockBackendRelay}/>);

        const table = screen.getByRole('table');
        expect(table).toBeInTheDocument();

        const cells = screen.getAllByRole('cell');
        expect(cells).toHaveLength(2);
        expect(cells[0]).toHaveTextContent('Дата проверки');
        expect(cells[1]).toHaveTextContent('03.03.2024');
    });
});
