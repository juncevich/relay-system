import {render, screen} from '@testing-library/react';

vi.mock('../components/mainTab/MainTab', () => ({
    default: () => <div data-testid="main-tab">MainTab Component</div>
}));

import MainPage from './MainPage';

describe('MainPage', () => {
    it('should render MainTab component', () => {
        render(<MainPage/>);

        expect(screen.getByTestId('main-tab')).toBeInTheDocument();
        expect(screen.getByText('MainTab Component')).toBeInTheDocument();
    });
});
