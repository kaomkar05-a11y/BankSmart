import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { apiRequest, getToken } from '../api.js';

const Dashboard = () => {
  const [modules, setModules] = useState([]);
  const [progress, setProgress] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const load = async () => {
      try {
        const modulesData = await apiRequest('/api/modules');
        setModules(modulesData);
        if (getToken()) {
          const progressData = await apiRequest('/api/progress');
          setProgress(progressData);
        }
      } catch (err) {
        setError('Unable to load progress. Please ensure the backend is running.');
      }
    };
    load();
  }, []);

  const completedCount = progress.length;

  return (
    <section className="mx-auto max-w-5xl space-y-6">
      <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
        <h2 className="text-2xl font-semibold text-navy-900">Learning Progress</h2>
        <p className="mt-2 text-sm text-slate-600">
          Track the counters you have completed and revisit any module before visiting a branch.
        </p>
        {error && <p className="mt-3 text-sm text-rose-700">{error}</p>}
        {!getToken() && (
          <p className="mt-3 text-sm text-amber-700">Sign in to save progress across devices.</p>
        )}
        <div className="mt-6 flex flex-wrap items-center gap-4">
          <div className="rounded-xl bg-slate-100 px-4 py-3 text-sm">
            <span className="font-semibold text-navy-900">{completedCount}</span> of{' '}
            <span className="font-semibold text-navy-900">{modules.length}</span> counters completed
          </div>
          <Link
            to="/hall"
            className="rounded-lg border border-slate-300 px-4 py-2 text-sm font-semibold text-slate-700"
          >
            Continue learning
          </Link>
        </div>
      </div>

      <div className="grid gap-4">
        {modules.map((module) => {
          const isComplete = progress.some((item) => item.moduleId === module.id);
          return (
            <div
              key={module.id}
              className="flex flex-col justify-between gap-4 rounded-2xl border border-slate-200 bg-white p-5 shadow-sm sm:flex-row"
            >
              <div>
                <h3 className="text-lg font-semibold text-navy-900">{module.title}</h3>
                <p className="mt-2 text-sm text-slate-600">{module.purpose}</p>
              </div>
              <div className="flex items-center gap-3">
                <span
                  className={`rounded-full px-3 py-1 text-xs font-semibold ${
                    isComplete
                      ? 'bg-emerald-100 text-emerald-700'
                      : 'bg-amber-100 text-amber-700'
                  }`}
                >
                  {isComplete ? 'Completed' : 'Not started'}
                </span>
                <Link
                  className="rounded-lg border border-slate-300 px-3 py-1 text-xs font-semibold text-slate-700"
                  to={`/modules/${module.id}`}
                >
                  View module
                </Link>
              </div>
            </div>
          );
        })}
      </div>
    </section>
  );
};

export default Dashboard;
