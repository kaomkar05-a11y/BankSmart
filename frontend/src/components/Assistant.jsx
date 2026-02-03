import PropTypes from 'prop-types';
import { useEffect, useState } from 'react';

const Assistant = ({ messages }) => {
  const [index, setIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(true);

  useEffect(() => {
    if (!isPlaying) {
      return;
    }
    const timer = setInterval(() => {
      setIndex((prev) => (prev + 1) % messages.length);
    }, 8000);
    return () => clearInterval(timer);
  }, [isPlaying, messages.length]);

  return (
    <div className="rounded-xl border border-slate-200 bg-white p-4 shadow-sm">
      <div className="flex items-start gap-3">
        <div className="flex h-10 w-10 items-center justify-center rounded-full bg-navy-800 text-white">
          BA
        </div>
        <div>
          <p className="text-xs font-semibold uppercase text-slate-500">Branch Assistant</p>
          <p className="mt-1 text-sm text-slate-700">{messages[index]}</p>
          <div className="mt-3 flex items-center gap-2">
            <button
              className="rounded border border-slate-300 px-2 py-1 text-xs"
              onClick={() => setIsPlaying((prev) => !prev)}
              type="button"
            >
              {isPlaying ? 'Pause' : 'Play'}
            </button>
            <button
              className="rounded border border-slate-300 px-2 py-1 text-xs"
              onClick={() => setIndex((prev) => (prev + 1) % messages.length)}
              type="button"
            >
              Skip
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

Assistant.propTypes = {
  messages: PropTypes.arrayOf(PropTypes.string).isRequired
};

export default Assistant;
