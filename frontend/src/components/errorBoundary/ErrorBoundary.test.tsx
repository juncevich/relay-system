import {fireEvent, render, screen} from '@testing-library/react';
import ErrorBoundary from './ErrorBoundary';

// Component that conditionally throws
const ProblematicComponent = ({ shouldThrow }: { shouldThrow: boolean }) => {
    if (shouldThrow) {
        throw new Error('Test error message');
    }
    return <div>No error</div>;
};

// Suppress console.error for cleaner test output
const originalError = console.error;
beforeAll(() => {
    console.error = vi.fn();
});
afterAll(() => {
    console.error = originalError;
});

describe('ErrorBoundary', () => {
    it('should render children when there is no error', () => {
        render(
            <ErrorBoundary>
                <div>Child content</div>
            </ErrorBoundary>
        );

        expect(screen.getByText('Child content')).toBeInTheDocument();
    });

    it('should render default error UI when child throws', () => {
        render(
            <ErrorBoundary>
                <ProblematicComponent shouldThrow={true} />
            </ErrorBoundary>
        );

        expect(screen.getByText('Произошла ошибка')).toBeInTheDocument();
        expect(screen.getByText('Test error message')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Попробовать снова' })).toBeInTheDocument();
    });

    it('should render custom fallback when provided', () => {
        render(
            <ErrorBoundary fallback={<div>Custom fallback</div>}>
                <ProblematicComponent shouldThrow={true} />
            </ErrorBoundary>
        );

        expect(screen.getByText('Custom fallback')).toBeInTheDocument();
        expect(screen.queryByText('Произошла ошибка')).not.toBeInTheDocument();
    });

    it('should have reset button visible after error', () => {
        render(
            <ErrorBoundary>
                <ProblematicComponent shouldThrow={true} />
            </ErrorBoundary>
        );

        // Verify error UI is shown with reset button
        expect(screen.getByText('Произошла ошибка')).toBeInTheDocument();
        const resetButton = screen.getByRole('button', { name: 'Попробовать снова' });
        expect(resetButton).toBeInTheDocument();

        // Click the button - this tests the handler fires without crashing
        fireEvent.click(resetButton);
    });

    it('should display error message in alert', () => {
        render(
            <ErrorBoundary>
                <ProblematicComponent shouldThrow={true} />
            </ErrorBoundary>
        );

        // Check error Alert structure
        expect(screen.getByRole('alert')).toBeInTheDocument();
        expect(screen.getByText('Произошла ошибка')).toBeInTheDocument();
        expect(screen.getByText('Test error message')).toBeInTheDocument();
    });
});
