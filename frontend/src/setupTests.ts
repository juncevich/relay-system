import '@testing-library/jest-dom';
import {configure} from '@testing-library/react';
import {vi} from 'vitest';

// Polyfill ResizeObserver for Ant Design components in jsdom
globalThis.ResizeObserver = class ResizeObserver {
  observe() {}
  unobserve() {}
  disconnect() {}
};

// Polyfill window.matchMedia for Ant Design responsive components in jsdom
Object.defineProperty(window, 'matchMedia', {
  writable: true,
  value: (query: string) => ({
    matches: false,
    media: query,
    onchange: null,
    addListener: () => {},
    removeListener: () => {},
    addEventListener: () => {},
    removeEventListener: () => {},
    dispatchEvent: () => false,
  }),
});

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
