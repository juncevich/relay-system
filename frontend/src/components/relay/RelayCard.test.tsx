import {render, screen} from '@testing-library/react';
import RelayCard from './RelayCard';
import {mockBackendRelay} from '../../test-utils/mockData';

describe('RelayCard Component', () => {
    it('should render relay serial number', () => {
        render(<RelayCard relay={mockBackendRelay}/>);
        expect(screen.getByText('НМШ-001')).toBeInTheDocument();
    });

    it('should render verification date label and value', () => {
        render(<RelayCard relay={mockBackendRelay}/>);
        expect(screen.getByText('Дата проверки')).toBeInTheDocument();
        expect(screen.getByText('03.03.2024')).toBeInTheDocument();
    });

    it('should render action buttons with correct aria-labels', () => {
        render(<RelayCard relay={mockBackendRelay}/>);
        expect(screen.getByLabelText('Редактировать')).toBeInTheDocument();
        expect(screen.getByLabelText('Удалить')).toBeInTheDocument();
    });

    it('should render status indicator with aria-label', () => {
        render(<RelayCard relay={mockBackendRelay}/>);
        expect(screen.getByLabelText('Статус реле')).toBeInTheDocument();
    });

    it('should render date in table format', () => {
        render(<RelayCard relay={mockBackendRelay}/>);
        const table = screen.getByRole('table');
        expect(table).toBeInTheDocument();
        const cells = screen.getAllByRole('cell');
        expect(cells[0]).toHaveTextContent('Дата проверки');
        expect(cells[1]).toHaveTextContent('03.03.2024');
    });

    it('should have relay-card-container wrapper', () => {
        const {container} = render(<RelayCard relay={mockBackendRelay}/>);
        expect(container.querySelector('.relay-card-container')).toBeInTheDocument();
    });

    it('should handle undefined relay prop gracefully', () => {
        render(<RelayCard/>);
        // Fallback dash for serial
        expect(screen.getByText('—')).toBeInTheDocument();
        // Hidden image still present
        expect(screen.getByAltText('Изображение реле')).toBeInTheDocument();
    });

    it('should show hidden image with correct alt text when relay is provided', () => {
        render(<RelayCard relay={mockBackendRelay}/>);
        expect(screen.getByAltText('Реле НМШ-001')).toBeInTheDocument();
    });
});
