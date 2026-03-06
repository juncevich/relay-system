import {defineConfig} from 'vite';
import react from '@vitejs/plugin-react';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
    plugins: [react(), tsconfigPaths()],
    server: {port: 3000},
    build: {
        outDir: 'build',
        chunkSizeWarningLimit: 600,
    },
    test: {
        globals: true,
        environment: 'jsdom',
        setupFiles: ['./test/setup-vitest.ts', './src/setupTests.ts'],
        exclude: ['e2e/**', 'node_modules/**'],
        css: {modules: {classNameStrategy: 'non-scoped'}},
    },
});
