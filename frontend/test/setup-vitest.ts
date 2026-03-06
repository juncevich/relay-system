const originalStderrWrite = process.stderr.write.bind(process.stderr);

process.stderr.write = ((chunk: string | Uint8Array, ...args: unknown[]) => {
    const text = typeof chunk === 'string' ? chunk : Buffer.from(chunk).toString('utf8');
    if (text.includes('Could not parse CSS stylesheet')) {
        return true;
    }
    return originalStderrWrite(chunk, ...(args as Parameters<typeof originalStderrWrite>));
}) as typeof process.stderr.write;
