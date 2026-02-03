import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { apiRequest, setToken } from '../api.js';

const AuthPage = () => {
  const [mode, setMode] = useState('login');
  const [form, setForm] = useState({ fullName: '', email: '', password: '' });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const submit = async (event) => {
    event.preventDefault();
    setMessage('');
    try {
      const payload = mode === 'register'
        ? { fullName: form.fullName, email: form.email, password: form.password }
        : { email: form.email, password: form.password };
      const data = await apiRequest(`/api/auth/${mode === 'register' ? 'register' : 'login'}`, {
        method: 'POST',
        body: JSON.stringify(payload)
      });
      setToken(data.token);
      navigate('/dashboard');
    } catch (err) {
      setMessage(err.message || 'Unable to authenticate.');
    }
  };

  return (
    <section className="mx-auto max-w-xl">
      <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
        <h2 className="text-2xl font-semibold text-navy-900">
          {mode === 'register' ? 'Create your BankSmart account' : 'Sign in to BankSmart'}
        </h2>
        <p className="mt-2 text-sm text-slate-600">
          Progress is stored securely so you can continue learning from any device.
        </p>
        <form className="mt-6 space-y-4" onSubmit={submit}>
          {mode === 'register' && (
            <div>
              <label className="text-xs font-semibold uppercase text-slate-500" htmlFor="fullName">
                Full name
              </label>
              <input
                className="mt-2 w-full rounded border border-slate-300 px-3 py-2 text-sm"
                id="fullName"
                name="fullName"
                onChange={(event) => setForm({ ...form, fullName: event.target.value })}
                required
                type="text"
                value={form.fullName}
              />
            </div>
          )}
          <div>
            <label className="text-xs font-semibold uppercase text-slate-500" htmlFor="email">
              Email
            </label>
            <input
              className="mt-2 w-full rounded border border-slate-300 px-3 py-2 text-sm"
              id="email"
              name="email"
              onChange={(event) => setForm({ ...form, email: event.target.value })}
              required
              type="email"
              value={form.email}
            />
          </div>
          <div>
            <label className="text-xs font-semibold uppercase text-slate-500" htmlFor="password">
              Password
            </label>
            <input
              className="mt-2 w-full rounded border border-slate-300 px-3 py-2 text-sm"
              id="password"
              name="password"
              onChange={(event) => setForm({ ...form, password: event.target.value })}
              required
              type="password"
              value={form.password}
            />
          </div>
          {message && <p className="text-sm text-rose-700">{message}</p>}
          <button className="w-full rounded-lg bg-navy-800 py-2 text-sm font-semibold text-white" type="submit">
            {mode === 'register' ? 'Create account' : 'Sign in'}
          </button>
        </form>
        <button
          className="mt-4 text-sm font-semibold text-navy-800"
          onClick={() => setMode(mode === 'register' ? 'login' : 'register')}
          type="button"
        >
          {mode === 'register' ? 'Already have an account? Sign in' : 'New here? Create an account'}
        </button>
      </div>
    </section>
  );
};

export default AuthPage;
