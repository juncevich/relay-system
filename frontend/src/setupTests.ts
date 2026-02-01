// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import '@testing-library/jest-dom';
import { configure } from '@testing-library/react';

// Configure React Testing Library for React 19
configure({ asyncUtilTimeout: 3000 });

// Mock rc-component warning system to prevent Ant Design warnings
jest.mock('@rc-component/util/lib/warning', () => {
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
  // Completely silence console.error for non-critical warnings
  console.error = jest.fn();
  console.warn = jest.fn();
});

afterAll(() => {
  console.error = originalError;
  console.warn = originalWarn;
});
