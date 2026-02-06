import {Component, ErrorInfo, ReactNode} from 'react';
import {Alert, Button} from 'antd';

interface ErrorBoundaryProps {
    children: ReactNode;
    fallback?: ReactNode;
}

interface ErrorBoundaryState {
    hasError: boolean;
    error: Error | null;
}

/**
 * Error Boundary component following React 19 best practices.
 * Catches JavaScript errors anywhere in child component tree,
 * logs errors, and displays fallback UI.
 */
class ErrorBoundary extends Component<ErrorBoundaryProps, ErrorBoundaryState> {
    constructor(props: ErrorBoundaryProps) {
        super(props);
        this.state = { hasError: false, error: null };
    }

    static getDerivedStateFromError(error: Error): ErrorBoundaryState {
        // Update state so the next render shows fallback UI
        return { hasError: true, error };
    }

    componentDidCatch(error: Error, info: ErrorInfo): void {
        // Log error to console in development
        if (import.meta.env.DEV) {
            console.error('ErrorBoundary caught an error:', error);
            console.error('Component stack:', info.componentStack);
        }

        // In production, you would log to an error reporting service
        // logErrorToService(error, info.componentStack);
    }

    handleReset = (): void => {
        this.setState({ hasError: false, error: null });
    };

    render(): ReactNode {
        if (this.state.hasError) {
            // Use custom fallback if provided
            if (this.props.fallback) {
                return this.props.fallback;
            }

            // Default fallback UI
            return (
                <Alert
                    message="Произошла ошибка"
                    description={
                        <>
                            <p>
                                {this.state.error?.message || 'Неизвестная ошибка приложения'}
                            </p>
                            <Button type="primary" onClick={this.handleReset}>
                                Попробовать снова
                            </Button>
                        </>
                    }
                    type="error"
                    showIcon
                />
            );
        }

        return this.props.children;
    }
}

export default ErrorBoundary;
