import { useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import { gsap } from 'gsap';

const Landing = () => {
  const heroRef = useRef(null);

  useEffect(() => {
    if (heroRef.current) {
      gsap.fromTo(
        heroRef.current,
        { opacity: 0, y: 24 },
        { opacity: 1, y: 0, duration: 0.8, ease: 'power2.out' }
      );
    }
  }, []);

  return (
    <section ref={heroRef} className="mx-auto grid max-w-6xl gap-10 lg:grid-cols-[1.2fr_0.8fr]">
      <div className="rounded-3xl bg-white p-8 shadow-lg">
        <p className="text-sm font-semibold uppercase tracking-wide text-gold-500">Virtual Branch Walkthrough</p>
        <h2 className="mt-4 text-3xl font-semibold text-navy-900 sm:text-4xl">
          Learn how real Indian bank branches work—before you step inside.
        </h2>
        <p className="mt-4 text-base text-slate-600">
          BankSmart guides first-time account holders through counters, forms, and documents used in real
          branches across India. Explore each counter, understand the exact steps, and arrive prepared.
        </p>
        <div className="mt-6 flex flex-wrap gap-4">
          <Link
            to="/hall"
            className="rounded-lg bg-navy-800 px-6 py-3 text-sm font-semibold text-white shadow hover:bg-navy-700"
          >
            Enter Bank Branch
          </Link>
          <Link
            to="/auth"
            className="rounded-lg border border-slate-300 px-6 py-3 text-sm font-semibold text-slate-700 hover:border-slate-400"
          >
            Sign in to track progress
          </Link>
        </div>
        <div className="mt-8 grid gap-4 sm:grid-cols-3">
          <div className="rounded-xl border border-slate-200 p-4">
            <p className="text-xs font-semibold uppercase text-slate-500">Real counters</p>
            <p className="mt-2 text-sm text-slate-600">Account opening, cash, passbook, loans, and ATM services.</p>
          </div>
          <div className="rounded-xl border border-slate-200 p-4">
            <p className="text-xs font-semibold uppercase text-slate-500">Step-by-step</p>
            <p className="mt-2 text-sm text-slate-600">Token collection, verification, approvals, and receipts.</p>
          </div>
          <div className="rounded-xl border border-slate-200 p-4">
            <p className="text-xs font-semibold uppercase text-slate-500">Form clarity</p>
            <p className="mt-2 text-sm text-slate-600">Know what to write and where, without guessing.</p>
          </div>
        </div>
      </div>
      <div className="rounded-3xl border border-slate-200 bg-slate-100 p-6 shadow-inner">
        <h3 className="text-lg font-semibold text-navy-900">What you will be able to do</h3>
        <ul className="mt-4 space-y-3 text-sm text-slate-700">
          <li>Identify which counter to approach for each service.</li>
          <li>Carry the right documents the first time.</li>
          <li>Fill forms correctly and avoid signature issues.</li>
          <li>Track your learning progress across all modules.</li>
        </ul>
        <div className="mt-6 rounded-xl bg-white p-4">
          <p className="text-xs font-semibold uppercase text-slate-500">Note</p>
          <p className="mt-2 text-sm text-slate-600">
            Processes may vary slightly by bank; BankSmart highlights common steps used across major public
            and private banks.
          </p>
        </div>
      </div>
    </section>
  );
};

export default Landing;
