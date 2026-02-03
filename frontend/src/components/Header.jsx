import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';
import { clearToken, getToken } from '../api.js';

const Header = ({ minimal }) => {
  const token = getToken();

  return (
    <header className="border-b border-slate-200 bg-white shadow-sm">
      <div className="mx-auto flex max-w-6xl items-center justify-between px-4 py-4 sm:px-8">
        <div>
          <h1 className="text-xl font-semibold text-navy-900">BankSmart Branch Guide</h1>
          {!minimal && (
            <p className="text-sm text-slate-600">Learn real bank processes before you visit.</p>
          )}
        </div>
        <nav className="flex items-center gap-4 text-sm font-medium text-slate-700">
          <NavLink className={({ isActive }) => (isActive ? 'text-navy-900' : '')} to="/">
            Home
          </NavLink>
          <NavLink className={({ isActive }) => (isActive ? 'text-navy-900' : '')} to="/hall">
            Branch Hall
          </NavLink>
          <NavLink className={({ isActive }) => (isActive ? 'text-navy-900' : '')} to="/dashboard">
            Progress
          </NavLink>
          {token ? (
            <button
              className="rounded border border-slate-300 px-3 py-1 text-slate-700 hover:border-slate-400"
              onClick={() => {
                clearToken();
                window.location.href = '/';
              }}
              type="button"
            >
              Sign out
            </button>
          ) : (
            <NavLink className="rounded bg-navy-800 px-3 py-1 text-white" to="/auth">
              Sign in
            </NavLink>
          )}
        </nav>
      </div>
    </header>
  );
};

Header.propTypes = {
  minimal: PropTypes.bool
};

Header.defaultProps = {
  minimal: false
};

export default Header;
