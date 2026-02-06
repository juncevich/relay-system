import '@testing-library/jest-dom';
import {configure} from '@testing-library/react';
import {afterAll, beforeAll, vi} from 'vitest';

// Configure React Testing Library for React 19
configure({ asyncUtilTimeout: 3000 });

// Mock rc-component warning system to prevent Ant Design warnings
vi.mock('@rc-component/util/lib/warning', () => {
  const mockWarning = () => {};
  mockWarning.noteOnce = () => {};
  mockWarning.note = () => {};
  mockWarning.resetWarned = () => {};
  mockWarning.call = () => {};
  return {
    __esModule: true,
    default: mockWarning,
    warning: mockWarning,
    note: () => {},
    resetWarned: () => {},
    call: () => {},
    noteOnce: () => {},
    warningOnce: () => {},
  };
});

// Suppress console warnings during tests
const originalError = console.error;
const originalWarn = console.warn;

beforeAll(() => {
  console.error = vi.fn();
  console.warn = vi.fn();
});

afterAll(() => {
  console.error = originalError;
  console.warn = originalWarn;
});
