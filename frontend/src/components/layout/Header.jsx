
import React from "react";
import { Menu } from "lucide-react";

function Header() {
    return (
        <div className="bg-white/80 dark:bg-slate-900/80 backdrop-blur-xl border-b border-slate-200/50 dark:border-slate-700/50 px-6 py-4"
        >
            <div className="flex items-center justify-between">
                {/* Left  */}
                <div className="flex items-center space-x-4">
                    <button className="p-2 rounded-lg text-slate-600 dark:text-slate-300 hover:bg-slate-100 dark:hover:bg-slate-800 transition-colors">
                        <Menu className="w-5 h-5"/>
                    </button>
                    <div className="hidden md:block">
                        <h1 className="text-2xl font-black text-slate-800 dark:text-white">
                            Dashboard
                        </h1>
                        <p className="text-xs text-slate-500 dark:text-slate-400">Willkommen zurück , Name! Beginn mit dem Lernen</p>
                    </div>
                    
                </div>

            </div>    
        </div>
    );
}

export default Header;