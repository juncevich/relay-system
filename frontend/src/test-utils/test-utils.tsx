import React from 'react';
import { render as rtlRender, RenderOptions, RenderResult } from '@testing-library/react';

/**
 * Custom render utilities for testing React 19 components.
 *
 * React 19 has stricter act() behavior that throws AggregateError
 * when async operations in useEffect are still pending after act() completes.
 * This file provides utilities to work around these limitations.
 */

// Re-export everything from React Testing Library
export * from '@testing-library/react';
