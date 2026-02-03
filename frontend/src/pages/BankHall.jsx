import { useEffect, useState } from 'react';
import { gsap } from 'gsap';
import Assistant from '../components/Assistant.jsx';
import ModuleCard from '../components/ModuleCard.jsx';
import { apiRequest } from '../api.js';

const assistantMessages = [
  'Welcome to the main hall. Select a counter to learn the exact steps used in Indian bank branches.',
  'Every counter includes required documents, real process steps, and common mistakes to avoid.',
  'Mark modules as completed to track your readiness before visiting a physical branch.'
];

const BankHall = () => {
  const [modules, setModules] = useState([]);
  const [completedIds, setCompletedIds] = useState(new Set());
  const [error, setError] = useState('');

  useEffect(() => {
    gsap.fromTo('.hall-card', { opacity: 0, y: 12 }, { opacity: 1, y: 0, stagger: 0.08 });
  }, [modules]);

  useEffect(() => {
    const loadData = async () => {
      try {
        const modulesData = await apiRequest('/api/modules');
        setModules(modulesData);
        const progressData = await apiRequest('/api/progress');
        setCompletedIds(new Set(progressData.map((item) => item.moduleId)));
      } catch (err) {
        setError('Sign in to track progress. You can still explore the modules.');
        try {
          const modulesData = await apiRequest('/api/modules');
          setModules(modulesData);
        } catch (modulesError) {
          setError('Unable to load module data. Please ensure the backend is running.');
        }
      }
    };
    loadData();
  }, []);

  return (
    <section className="mx-auto max-w-6xl space-y-6">
      <div className="flex flex-col gap-6 lg:flex-row">
        <div className="flex-1">
          <h2 className="text-2xl font-semibold text-navy-900">Main Bank Hall</h2>
          <p className="mt-2 text-sm text-slate-600">
            Click a counter to explore detailed instructions, realistic form previews, and common mistakes.
          </p>
          {error && <p className="mt-3 text-sm text-amber-700">{error}</p>}
        </div>
        <div className="w-full lg:max-w-sm">
          <Assistant messages={assistantMessages} />
        </div>
      </div>

      <div className="grid gap-5 sm:grid-cols-2">
        {modules.map((module) => (
          <div className="hall-card" key={module.id}>
            <ModuleCard module={module} completed={completedIds.has(module.id)} />
          </div>
        ))}
      </div>
    </section>
  );
};

export default BankHall;
