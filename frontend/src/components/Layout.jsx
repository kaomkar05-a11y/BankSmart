import PropTypes from 'prop-types';
import { useLocation } from 'react-router-dom';
import Header from './Header.jsx';

const Layout = ({ children }) => {
  const location = useLocation();
  const isLanding = location.pathname === '/';

  return (
    <div className="min-h-screen bg-slate-50 text-slate-900">
      <Header minimal={isLanding} />
      <main className="px-4 pb-16 pt-6 sm:px-8 lg:px-12">{children}</main>
    </div>
  );
};

Layout.propTypes = {
  children: PropTypes.node.isRequired
};

export default Layout;
