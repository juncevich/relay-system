import {render, screen} from '@testing-library/react';
import RelayCard from './RelayCard';
import {mockLegacyRelay} from '../../test-utils/mockData';

describe('RelayCard Component', () => {
    it('should render relay card with all data', () => {
        render(<RelayCard relay={mockLegacyRelay} />);

        expect(screen.getByAltText('Реле REL-001')).toBeInTheDocument();
        expect(screen.getByText('REL-001')).toBeInTheDocument();
        expect(screen.getByText('Дата проверки')).toBeInTheDocument();
        expect(screen.getByText('01.02.2024')).toBeInTheDocument();
    });

    it('should render image with correct src', () => {
        render(<RelayCard relay={mockLegacyRelay} />);

        const image = screen.getByAltText('Реле REL-001') as HTMLImageElement;
        expect(image.src).toBe(mockLegacyRelay.imgUrl);
    });

    it('should render all action buttons with correct aria-labels', () => {
        render(<RelayCard relay={mockLegacyRelay} />);

        expect(screen.getByLabelText('Настройки')).toBeInTheDocument();
        expect(screen.getByLabelText('Редактировать')).toBeInTheDocument();
        expect(screen.getByLabelText('Дополнительные действия')).toBeInTheDocument();
    });

    it('should render status icon with correct aria-label', () => {
        render(<RelayCard relay={mockLegacyRelay} />);

        expect(screen.getByLabelText('Статус реле')).toBeInTheDocument();
    });

    it('should handle undefined relay prop', () => {
        render(<RelayCard />);

        expect(screen.getByAltText('Изображение реле')).toBeInTheDocument();
        expect(screen.getByText('Дата проверки')).toBeInTheDocument();
    });

    it('should render with correct CSS class', () => {
        const { container } = render(<RelayCard relay={mockLegacyRelay} />);

        expect(container.querySelector('.relay-card-container')).toBeInTheDocument();
    });

    it('should display checking date in table format', () => {
        render(<RelayCard relay={mockLegacyRelay} />);

        const table = screen.getByRole('table');
        expect(table).toBeInTheDocument();

        const cells = screen.getAllByRole('cell');
        expect(cells).toHaveLength(2);
        expect(cells[0]).toHaveTextContent('Дата проверки');
        expect(cells[1]).toHaveTextContent('01.02.2024');
    });
});
