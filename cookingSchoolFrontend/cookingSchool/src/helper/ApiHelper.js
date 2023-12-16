export function createApiUrl(path) {
    const normalizedPath = path.startsWith('/') ? path.substring(1) : path
    return `http://localhost:8082/${normalizedPath}`
}