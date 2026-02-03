const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080';

export async function apiRequest(path, options = {}) {
  const token = localStorage.getItem('banksmart_token');
  const headers = {
    'Content-Type': 'application/json',
    ...(options.headers || {})
  };
  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }
  const response = await fetch(`${API_BASE}${path}`, {
    ...options,
    headers
  });
  if (!response.ok) {
    const errorBody = await response.json().catch(() => ({}));
    const message = errorBody.message || 'Request failed';
    throw new Error(message);
  }
  if (response.status === 204) {
    return null;
  }
  return response.json();
}

export function setToken(token) {
  localStorage.setItem('banksmart_token', token);
}

export function clearToken() {
  localStorage.removeItem('banksmart_token');
}

export function getToken() {
  return localStorage.getItem('banksmart_token');
}
