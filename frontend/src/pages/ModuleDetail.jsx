import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { gsap } from 'gsap';
import Assistant from '../components/Assistant.jsx';
import { apiRequest, getToken } from '../api.js';

const ModuleDetail = () => {
  const { id } = useParams();
  const [module, setModule] = useState(null);
  const [statusMessage, setStatusMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const loadModule = async () => {
      try {
        const data = await apiRequest(`/api/modules/${id}`);
        setModule(data);
        gsap.fromTo('.detail-section', { opacity: 0, y: 16 }, { opacity: 1, y: 0, stagger: 0.1 });
      } catch (err) {
        setError('Unable to load the module. Please check the backend service.');
      }
    };
    loadModule();
  }, [id]);

  const markComplete = async () => {
    try {
      await apiRequest(`/api/progress/${id}/complete`, { method: 'POST' });
      setStatusMessage('Marked as completed. Your progress dashboard is updated.');
    } catch (err) {
      setStatusMessage('Please sign in to save progress.');
    }
  };

  if (error) {
    return <p className="text-sm text-rose-700">{error}</p>;
  }

  if (!module) {
    return <p className="text-sm text-slate-600">Loading counter details...</p>;
  }

  return (
    <section className="mx-auto max-w-6xl space-y-6">
      <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
        <div className="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
          <div>
            <p className="text-xs font-semibold uppercase text-slate-500">Counter Module</p>
            <h2 className="mt-2 text-2xl font-semibold text-navy-900">{module.title}</h2>
            <p className="mt-2 text-sm text-slate-600">{module.purpose}</p>
          </div>
          <div>
            <button
              className="rounded-lg bg-emerald-600 px-5 py-2 text-sm font-semibold text-white hover:bg-emerald-500"
              onClick={markComplete}
              type="button"
            >
              Mark as completed
            </button>
            {statusMessage && <p className="mt-2 text-xs text-slate-600">{statusMessage}</p>}
            {!getToken() && (
              <p className="mt-2 text-xs text-amber-700">Sign in to store completion status.</p>
            )}
          </div>
        </div>
      </div>

      <div className="grid gap-6 lg:grid-cols-[2fr_1fr]">
        <div className="space-y-6">
          <div className="detail-section rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
            <h3 className="text-lg font-semibold text-navy-900">Required Documents</h3>
            <ul className="mt-3 list-disc space-y-2 pl-5 text-sm text-slate-700">
              {module.requiredDocuments.map((doc) => (
                <li key={doc}>{doc}</li>
              ))}
            </ul>
          </div>
          <div className="detail-section rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
            <h3 className="text-lg font-semibold text-navy-900">Step-by-Step Process</h3>
            <ol className="mt-3 list-decimal space-y-2 pl-5 text-sm text-slate-700">
              {module.processSteps.map((step) => (
                <li key={step}>{step}</li>
              ))}
            </ol>
          </div>
          <div className="detail-section rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
            <h3 className="text-lg font-semibold text-navy-900">Common Mistakes to Avoid</h3>
            <ul className="mt-3 list-disc space-y-2 pl-5 text-sm text-slate-700">
              {module.commonMistakes.map((mistake) => (
                <li key={mistake}>{mistake}</li>
              ))}
            </ul>
          </div>
        </div>
        <div className="space-y-6">
          <Assistant
            messages={[
              'Use the form preview to understand exactly where each detail should be written.',
              'If any step differs in your local branch, confirm with the desk officer politely.',
              'Carry original documents even when photocopies are required for submission.'
            ]}
          />
          <div className="detail-section rounded-2xl border border-slate-200 bg-white p-6 shadow-sm">
            <h3 className="text-lg font-semibold text-navy-900">Sample Form Preview</h3>
            <div className="mt-3 space-y-3 rounded-xl border border-dashed border-slate-200 bg-slate-50 p-4">
              <p className="text-xs font-semibold uppercase text-slate-500">Key fields to fill</p>
              <div className="space-y-3">
                {module.formFields?.map((field) => (
                  <div key={field.label} className="rounded-lg border border-slate-200 bg-white p-3 text-sm">
                    <p className="font-semibold text-navy-900">{field.label}</p>
                    <p className="mt-1 text-slate-600">{field.guidance}</p>
                    <p className="mt-2 text-xs text-rose-700">Avoid: {field.caution}</p>
                  </div>
                ))}
              </div>
            </div>
            <h4 className="mt-5 text-sm font-semibold text-navy-900">Form Preview Highlights</h4>
            <div className="mt-3 space-y-3">
              {module.formHighlights.map((highlight) => (
                <div key={highlight} className="rounded-lg border border-slate-200 bg-slate-50 p-3 text-sm text-slate-700">
                  {highlight}
                </div>
              ))}
            </div>
            <p className="mt-4 text-xs text-slate-500">
              These layouts reflect common public-sector bank forms. Some fields may move slightly depending on the bank.
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default ModuleDetail;
