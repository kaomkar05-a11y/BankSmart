import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

const ModuleCard = ({ module, completed }) => {
  return (
    <Link
      to={`/modules/${module.id}`}
      className="group rounded-2xl border border-slate-200 bg-white p-5 shadow-sm transition hover:-translate-y-1 hover:border-slate-300"
    >
      <div className="flex items-center justify-between">
        <h3 className="text-lg font-semibold text-navy-900">{module.title}</h3>
        {completed && (
          <span className="rounded-full bg-emerald-100 px-3 py-1 text-xs font-semibold text-emerald-700">
            Completed
          </span>
        )}
      </div>
      <p className="mt-3 text-sm text-slate-600">{module.purpose}</p>
      <div className="mt-4 text-xs font-semibold text-navy-800">View counter details →</div>
    </Link>
  );
};

ModuleCard.propTypes = {
  module: PropTypes.shape({
    id: PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    purpose: PropTypes.string.isRequired
  }).isRequired,
  completed: PropTypes.bool
};

ModuleCard.defaultProps = {
  completed: false
};

export default ModuleCard;
