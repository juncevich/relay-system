import {render, screen} from '@testing-library/react';
import HomePage from './HomePage';

describe('HomePage', () => {
    it('should render "Main" text', () => {
        render(<HomePage/>);

        expect(screen.getByText('Main')).toBeInTheDocument();
    });
});
