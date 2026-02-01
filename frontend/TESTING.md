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

The project uses Create React App's built-in testing setup:

- **Jest**: Test runner and assertion library
- **React Testing Library**: Component testing utilities
- **@testing-library/user-event**: User interaction simulation
- **@testing-library/jest-dom**: Custom Jest matchers for DOM assertions

All dependencies are already installed with Create React App.

## Running Tests

```bash
# Run tests in watch mode (interactive)
npm test

# Run all tests once
npm test -- --watchAll=false

# Run tests with coverage
npm test -- --coverage --watchAll=false

# Run specific test file
npm test -- RelayCard.test.tsx

# Run tests matching a pattern
npm test -- --testNamePattern="should render"
```

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
    const onSettingsClick = jest.fn();

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

// Mock the API services
jest.mock('../../api/RelayService');
jest.mock('../../api/LocationService');

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
    (RelayService.getAll as jest.Mock).mockResolvedValue({
      content: mockRelays,
      totalElements: 1
    });

    (LocationService.getAllStations as jest.Mock).mockResolvedValue({
      content: mockStations,
      totalElements: 1
    });
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
    (RelayService.getAll as jest.Mock).mockRejectedValue(
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

### Mocking Axios Requests

```typescript
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

// Create a mock adapter
const mock = new MockAdapter(axios);

describe('RelayService', () => {
  afterEach(() => {
    mock.reset();
  });

  it('should fetch all relays', async () => {
    const mockData = {
      content: [{ id: 1, type: 'РПА-30' }],
      totalElements: 1
    };

    mock.onGet('/relays').reply(200, mockData);

    const result = await RelayService.getAll();
    expect(result.content).toHaveLength(1);
  });
});
```

### Mocking Service Modules

```typescript
// Mock entire module
jest.mock('../../api/RelayService', () => ({
  getAll: jest.fn(),
  getById: jest.fn(),
  create: jest.fn(),
  update: jest.fn(),
  delete: jest.fn()
}));

// Use in tests
import * as RelayService from '../../api/RelayService';

(RelayService.getAll as jest.Mock).mockResolvedValue({
  content: [],
  totalElements: 0
});
```

### Partial Mocking

```typescript
// Mock only specific methods
jest.mock('../../api/RelayService', () => ({
  ...jest.requireActual('../../api/RelayService'),
  getAll: jest.fn()
}));
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
    jest.clearAllMocks();
  });

  afterEach(() => {
    // Cleanup after each test
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
  const onSubmit = jest.fn();

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
npm test -- --coverage --watchAll=false
```

Coverage reports are generated in the `coverage/` directory.

## React 19 Testing Considerations

React 19 introduces stricter `act()` behavior that affects testing components with async `useEffect`. When a component triggers async operations in `useEffect`, React 19's `act()` may throw an `AggregateError` if those operations are still pending when `act()` completes.

### Known Limitations

Components with complex async data fetching patterns (like `MainTab`) may be difficult to test with unit tests due to this stricter behavior. Consider these alternatives:

1. **E2E Testing**: Use Playwright or Cypress for integration-level testing of components with complex async behavior
2. **Simplified Tests**: Test individual behaviors rather than full component lifecycle
3. **Mock Sync Responses**: Use `mockResolvedValueOnce()` with sync-like behavior where possible

### Components Without Unit Tests

The following components are better suited for E2E testing:

- `MainTab` - Complex async data fetching with multiple API calls and state updates

### Workaround for Async useEffect

If you must test a component with async `useEffect`, try this pattern:

```typescript
import { waitFor } from '@testing-library/react';

it('should load data', async () => {
    // Mock to resolve immediately
    mockService.getData.mockResolvedValue({ data: mockData });

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

- [React Testing Library Documentation](https://testing-library.com/react)
- [Jest Documentation](https://jestjs.io/)
- [Testing Ant Design Components](https://ant.design/docs/react/testing)
- [Common Testing Mistakes](https://kentcdodds.com/blog/common-mistakes-with-react-testing-library)
- [React 19 Act Testing](https://react.dev/reference/react/act)
