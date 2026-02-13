# Testing Guide

This guide provides testing patterns and best practices for the relay management system frontend.

## Table of Contents

- [Testing Stack](#testing-stack)
- [Running Tests](#running-tests)
- [Testing Patterns](#testing-patterns)
- [Component Testing](#component-testing)
- [API Mocking](#api-mocking)
- [Testing Ant Design Components](#testing-ant-design-components)
- [Best Practices](#best-practices)
- [Common Testing Scenarios](#common-testing-scenarios)

## Testing Stack

The project uses Vitest for testing with the following tools:

- **Vitest 4**: Test runner and assertion library (replaces Jest)
- **React Testing Library**: Component testing utilities
- **@testing-library/user-event**: User interaction simulation
- **@testing-library/jest-dom**: Custom matchers for DOM assertions (works with Vitest)
- **jsdom**: DOM environment for testing
- **@vitest/coverage-v8**: Code coverage reporting

All dependencies are already installed and configured with Vite.

## Running Tests

```bash
# Run all tests once
npm test

# Run tests in watch mode (interactive)
npm run test:watch

# Run tests with coverage
npm test -- --coverage

# Run specific test file
npm test RelayCard.test.tsx

# Run tests matching a pattern
npm test -- --grep="should render"
```

Current test status: **74 tests across 11 test files, all passing**

## Testing Patterns

### Basic Component Test Structure

```typescript
import { render, screen } from '@testing-library/react';
import { ComponentName } from './ComponentName';

describe('ComponentName', () => {
  it('should render successfully', () => {
    render(<ComponentName />);
    // Assertions here
  });

  it('should handle user interaction', async () => {
    const user = userEvent.setup();
    render(<ComponentName />);

    await user.click(screen.getByRole('button', { name: /click me/i }));
    // Assertions here
  });
});
```

### Testing with Props

```typescript
import { Relay } from '../../models/Relay';

const mockRelay: Relay = {
  imgUrl: '/images/relay.png',
  title: 'Test Relay',
  checkingDate: '2026-01-15'
};

it('should display relay information', () => {
  render(<RelayCard relay={mockRelay} />);

  expect(screen.getByText('Test Relay')).toBeInTheDocument();
  expect(screen.getByText('2026-01-15')).toBeInTheDocument();
});
```

**Note:** With Vitest, globals like `describe`, `it`, `expect`, and `vi` are available automatically (configured in
`vite.config.ts` and `tsconfig.json`).

### Testing Async Operations

```typescript
import { waitFor } from '@testing-library/react';

it('should load relays from API', async () => {
  render(<MainTab />);

  // Wait for loading to complete
  await waitFor(() => {
    expect(screen.queryByRole('progressbar')).not.toBeInTheDocument();
  });

  // Verify data is displayed
  expect(screen.getByText(/relay/i)).toBeInTheDocument();
});
```

## Component Testing

### Testing RelayCard Component

```typescript
// src/components/relay/RelayCard.test.tsx
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { RelayCard } from './RelayCard';
import { Relay } from '../../models/Relay';

const mockRelay: Relay = {
  imgUrl: '/images/relay-rpa.png',
  title: 'РПА-30 №123',
  checkingDate: '2026-01-15'
};

describe('RelayCard', () => {
  it('should render relay card with correct data', () => {
    render(<RelayCard relay={mockRelay} />);

    expect(screen.getByText('РПА-30 №123')).toBeInTheDocument();
    expect(screen.getByText('2026-01-15')).toBeInTheDocument();
    expect(screen.getByAltText('relay')).toHaveAttribute('src', '/images/relay-rpa.png');
  });

  it('should render all action buttons', () => {
    render(<RelayCard relay={mockRelay} />);

    const buttons = screen.getAllByRole('button');
    expect(buttons).toHaveLength(3); // Settings, Edit, Ellipsis
  });

  it('should handle settings button click', async () => {
    const user = userEvent.setup();
      const onSettingsClick = vi.fn();

    render(<RelayCard relay={mockRelay} onSettingsClick={onSettingsClick} />);

    const settingsButton = screen.getAllByRole('button')[0];
    await user.click(settingsButton);

    expect(onSettingsClick).toHaveBeenCalledTimes(1);
  });
});
```

### Testing MainTab Component

```typescript
// src/components/mainTab/MainTab.test.tsx
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MainTab } from './MainTab';
import * as RelayService from '../../api/RelayService';
import * as LocationService from '../../api/LocationService';

// Mock the API services (Vitest syntax)
vi.mock('../../api/RelayService');
vi.mock('../../api/LocationService');

const mockRelays = [
  {
    id: 1,
    type: 'РПА-30',
    serialNumber: '123',
    checkingDate: '2026-01-15',
    status: 'ACTIVE'
  }
];

const mockStations = [
  {
    id: 1,
    name: 'Свердловский участок',
    type: 'STATION'
  }
];

describe('MainTab', () => {
  beforeEach(() => {
      // Use vi.mocked for type-safe mocking
      vi.mocked(RelayService.getAll).mockResolvedValue({
          data: {
              content: mockRelays,
              totalElements: 1
          }
      } as any);

      vi.mocked(LocationService.getAllStations).mockResolvedValue({
          data: {
              content: mockStations,
              totalElements: 1
          }
      } as any);
  });

  it('should show loading spinner initially', () => {
    render(<MainTab />);
    expect(screen.getByRole('progressbar')).toBeInTheDocument();
  });

  it('should load and display relays', async () => {
    render(<MainTab />);

    await waitFor(() => {
      expect(screen.queryByRole('progressbar')).not.toBeInTheDocument();
    });

    expect(screen.getByText(/РПА-30/i)).toBeInTheDocument();
  });

  it('should display error message on API failure', async () => {
      vi.mocked(RelayService.getAll).mockRejectedValue(
      new Error('Network error')
    );

    render(<MainTab />);

    await waitFor(() => {
      expect(screen.getByText(/ошибка при загрузке данных/i)).toBeInTheDocument();
    });
  });

  it('should switch tabs', async () => {
    const user = userEvent.setup();
    render(<MainTab />);

    await waitFor(() => {
      expect(screen.queryByRole('progressbar')).not.toBeInTheDocument();
    });

    const tabButtons = screen.getAllByRole('tab');
    await user.click(tabButtons[1]); // Click second tab

    expect(tabButtons[1]).toHaveAttribute('aria-selected', 'true');
  });
});
```

## API Mocking

### Mocking Service Modules with Vitest

```typescript
// Mock entire module
vi.mock('../../api/RelayService', () => ({
    default: {
        getAll: vi.fn(),
        getById: vi.fn(),
        create: vi.fn(),
        update: vi.fn(),
        delete: vi.fn()
    }
}));

// Use in tests
import relayService from '../../api/RelayService';

vi.mocked(relayService.getAll).mockResolvedValue({
    data: {
        content: [],
        totalElements: 0
    }
} as any);
```

### Partial Mocking

```typescript
// Mock only specific methods
vi.mock('../../api/RelayService', async () => {
    const actual = await vi.importActual('../../api/RelayService');
    return {
        ...actual,
        getAll: vi.fn()
    };
});
```

### Type-Safe Mocking

For better type safety with mocked functions, use the `Mocked` type from vitest:

```typescript
import type {Mocked} from 'vitest';
import relayService from '../../api/RelayService';

// Type assertion
const mockRelayService = relayService as Mocked<typeof relayService>;

// Now you get full TypeScript support
mockRelayService.getAll.mockResolvedValue({...});
```

## Testing Ant Design Components

### Testing Menu Navigation

```typescript
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

it('should navigate when menu item is clicked', async () => {
  const user = userEvent.setup();
  render(<MainTab />);

  // Find menu item by text
  const menuItem = screen.getByText('Свердловский участок');
  await user.click(menuItem);

  // Verify navigation or state change
  expect(screen.getByText(/свердловский/i)).toBeInTheDocument();
});
```

### Testing Tabs

```typescript
it('should switch between tabs', async () => {
  const user = userEvent.setup();
  render(<MainTab />);

  // Get all tab elements
  const tabs = screen.getAllByRole('tab');

  // Click second tab
  await user.click(tabs[1]);

  // Verify active state
  expect(tabs[1]).toHaveClass('ant-tabs-tab-active');
});
```

### Testing Cards

```typescript
it('should render card with actions', () => {
  render(<RelayCard relay={mockRelay} />);

  // Card should be in document
  const card = screen.getByRole('img', { name: /relay/i }).closest('.ant-card');
  expect(card).toBeInTheDocument();

  // Card actions should render
  const buttons = screen.getAllByRole('button');
  expect(buttons.length).toBeGreaterThan(0);
});
```

### Testing Modals and Drawers

```typescript
it('should open modal on button click', async () => {
  const user = userEvent.setup();
  render(<ComponentWithModal />);

  // Click button to open modal
  await user.click(screen.getByRole('button', { name: /open/i }));

  // Modal should be visible
  expect(screen.getByRole('dialog')).toBeInTheDocument();

  // Close modal
  await user.click(screen.getByRole('button', { name: /cancel/i }));

  // Modal should be closed
  await waitFor(() => {
    expect(screen.queryByRole('dialog')).not.toBeInTheDocument();
  });
});
```

## Best Practices

### 1. Query Priority

Use queries in this order of preference:

```typescript
// 1. Accessible queries (best)
screen.getByRole('button', { name: /submit/i })
screen.getByLabelText('Email')

// 2. Semantic queries
screen.getByText('Welcome')
screen.getByAltText('Profile picture')

// 3. Test IDs (last resort)
screen.getByTestId('custom-element')
```

### 2. Async Testing

```typescript
// Use waitFor for async assertions
await waitFor(() => {
  expect(screen.getByText('Loaded')).toBeInTheDocument();
});

// Use findBy for queries that wait
const element = await screen.findByText('Loaded');

// Don't use arbitrary timeouts
// ❌ Bad
setTimeout(() => { /* test */ }, 1000);

// ✅ Good
await waitFor(() => { /* test */ });
```

### 3. User Interactions

```typescript
// Always setup user for interactions
const user = userEvent.setup();

// Prefer userEvent over fireEvent
await user.click(button);
await user.type(input, 'text');
await user.keyboard('{Enter}');
```

### 4. Test Isolation

```typescript
describe('Component', () => {
  beforeEach(() => {
    // Reset mocks before each test
      vi.clearAllMocks();
  });

  afterEach(() => {
      // Cleanup after each test (usually automatic with Vitest)
    cleanup();
  });
});
```

### 5. Testing Custom Hooks

```typescript
import { renderHook, waitFor } from '@testing-library/react';

it('should fetch data with custom hook', async () => {
  const { result } = renderHook(() => useRelays());

  await waitFor(() => {
    expect(result.current.loading).toBe(false);
  });

  expect(result.current.relays).toHaveLength(5);
});
```

## Common Testing Scenarios

### Testing Error Boundaries

```typescript
it('should catch errors and display fallback', () => {
  const ThrowError = () => {
    throw new Error('Test error');
  };

  render(
    <ErrorBoundary>
      <ThrowError />
    </ErrorBoundary>
  );

  expect(screen.getByText(/something went wrong/i)).toBeInTheDocument();
});
```

### Testing Loading States

```typescript
it('should show loading spinner while fetching', () => {
  render(<MainTab />);
  expect(screen.getByRole('progressbar')).toBeInTheDocument();
});

it('should hide loading spinner after data loads', async () => {
  render(<MainTab />);

  await waitFor(() => {
    expect(screen.queryByRole('progressbar')).not.toBeInTheDocument();
  });
});
```

### Testing Forms

```typescript
import userEvent from '@testing-library/user-event';

it('should submit form with correct data', async () => {
  const user = userEvent.setup();
    const onSubmit = vi.fn();

  render(<RelayForm onSubmit={onSubmit} />);

  await user.type(screen.getByLabelText(/тип/i), 'РПА-30');
  await user.type(screen.getByLabelText(/серийный номер/i), '123');
  await user.click(screen.getByRole('button', { name: /сохранить/i }));

  expect(onSubmit).toHaveBeenCalledWith({
    type: 'РПА-30',
    serialNumber: '123'
  });
});
```

### Testing Accessibility

```typescript
import { axe, toHaveNoViolations } from 'jest-axe';

expect.extend(toHaveNoViolations);

it('should have no accessibility violations', async () => {
  const { container } = render(<RelayCard relay={mockRelay} />);
  const results = await axe(container);
  expect(results).toHaveNoViolations();
});
```

### Testing TypeScript Types

```typescript
// Type assertions in tests
it('should have correct type', () => {
  const relay: Relay = {
    imgUrl: '/test.png',
    title: 'Test',
    checkingDate: '2026-01-01'
  };

  // TypeScript will catch type errors at compile time
  expect(relay.title).toBe('Test');
});

// Test type guards
it('should validate relay type', () => {
  const data = { imgUrl: '/test.png', title: 'Test' };

  expect(isValidRelay(data)).toBe(false); // Missing checkingDate
});
```

## Coverage Goals

Aim for these coverage targets:

- **Statements**: 80%+
- **Branches**: 75%+
- **Functions**: 80%+
- **Lines**: 80%+

```bash
npm test -- --coverage
```

Coverage reports are generated in the `coverage/` directory using Vitest's V8 coverage provider.

## Vitest-Specific Features

### Global Test APIs

Vitest provides globals (`describe`, `it`, `expect`, `vi`) automatically when configured in `vite.config.ts`:

```typescript
// vite.config.ts
test: {
    globals: true,
        environment
:
    'jsdom',
        setupFiles
:
    './src/setupTests.ts',
}
```

No need to import `describe`, `it`, or `expect` in test files.

### Mock Functions

Use `vi` instead of `jest`:

```typescript
// Create mock function
const mockFn = vi.fn();

// Mock implementation
mockFn.mockImplementation(() => 'result');

// Mock return value
mockFn.mockReturnValue('result');

// Mock resolved value (async)
mockFn.mockResolvedValue({data: []});

// Clear mocks
vi.clearAllMocks();

// Reset mocks
vi.resetAllMocks();
```

### TypeScript Support

For better TypeScript support with mocks:

```typescript
import type {Mocked} from 'vitest';

const mockService = service as Mocked<typeof service>;
```

**Note:** Use `import type { Mocked }` (not `vi.Mocked`).

## React 19 Testing Considerations

React 19 introduces stricter `act()` behavior that affects testing components with async `useEffect`. When a component triggers async operations in `useEffect`, React 19's `act()` may throw an `AggregateError` if those operations are still pending when `act()` completes.

### Best Practices

1. **Mock API calls** to resolve immediately
2. **Use waitFor** to wait for loading states to complete
3. **Test individual behaviors** rather than full component lifecycle
4. **Consider E2E tests** for complex async components (Playwright, Cypress)

### Workaround for Async useEffect

```typescript
import { waitFor } from '@testing-library/react';

it('should load data', async () => {
    // Mock to resolve immediately
    vi.mocked(mockService.getData).mockResolvedValue({data: mockData});

    render(<Component />);

    // Wait for async operations to complete
    await waitFor(() => {
        expect(screen.queryByText('Loading...')).not.toBeInTheDocument();
    });

    // Assert on loaded state
    expect(screen.getByText('Data loaded')).toBeInTheDocument();
});
```

## Additional Resources

- [Vitest Documentation](https://vitest.dev/)
- [React Testing Library Documentation](https://testing-library.com/react)
- [Testing Ant Design Components](https://ant.design/docs/react/testing)
- [Common Testing Mistakes](https://kentcdodds.com/blog/common-mistakes-with-react-testing-library)
- [React 19 Act Testing](https://react.dev/reference/react/act)
