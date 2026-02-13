import { Zap, LayoutDashboard, Users, Settings, ChevronDown } from "lucide-react";
import React,{useState} from "react";

const menuItems = [
    {
        id: "dashboard",
        icon: LayoutDashboard,
        label: "Dashboard",
        active: true,
        badge: "New"
    },
    {
        id: "users",
        icon: Users,
        label: "Users",
        badge: "0",
        submenu: [
            { id: "all-users", label: "All Users" },
            { id: "roles", label: "Roles" },
            { id: "activity", label: "Activity" },
        ],
    }
];
function Sidebar({ collapsed, onToggle, currentPage, onPageChange }) {

    const [expandedItem, setExpandedItem] = useState(new Set(['analytics']));

    const toggleExpanded = (itemid) => {
        const newExpanded = new Set(expandedItem);
        if (newExpanded.has(itemid)) {
            newExpanded.delete(itemid);
        } else {
            newExpanded.add(itemid);
        }
        setExpandedItem(newExpanded);
    };


    return (
        <div className={`${collapsed ? "w-20" : "w-72"} transition duration-300 ease-in-out bg-white/95 backdrop-blur-xl  flex flex-col relative z-10`}>
            {/* Logo*/}
            <div className="p-6">
                <div className="flex items-center space-x-3">
                    <div className="w-10 h-10 bg-gradient-to-r from-blue-600 to-purple-600 rounded-xl flex items-center justify-center shadow-lg">
                        <Zap className="w-6 h-6 text-white" />
                    </div>
                    {/*Conditional Rendering */}
                    {!collapsed && (<div>
                        <h1 className="text-xl font-bold text-slate-800 ">
                            Name
                        </h1>
                        <p className="text-xs text-slate-500">
                            Panel Type
                        </p>
                    </div>)}
                </div>
            </div>

            {/*Navigation */}
            <nav className="flex-1 p-4 space-y-2 overflow-y-auto">
                {menuItems.map((item) => (
                    <div key={item.id}>
                        <button 
                        className={`w-full flex items-center justify-between p-3 rounded-xl transition-all duration-200  ${currentPage === item.id || item.active ? "bg-gradient-to-r from-blue-600 to-purple-600 text-white shadow-lg shadow-blue-500/25" : "text-gray-600 hover:bg-slate-100"
                        }`}
                        onClick={() => {
                            if(item.submenu){
                                toggleExpanded(item.id)
                            }else{
                                onPageChange(item.id)
                            }
                        }}  
                        >
                            <div className="flex items-center space-x-3">
                                <item.icon className="w-5 h-5 text-gray-600" />
                                {/*Conditional Rendering */}
                                <>
                                    {!collapsed && (
                                        <span className="font-medium ml-2 bg-gradient-to-r from-blue-600 to-purple-600 bg-clip-text ">{item.label}</span>
                                    )}
                                    {item.badge && (
                                        <span className="px-2 py-1 text-xs bg-slate-200 text-slate-600 rounded-full">
                                            {item.badge}
                                        </span>
                                    )}
                                </>
                            </div>

                            {!collapsed && item.submenu && (
                                <ChevronDown className={`w-4 h-4 text-gray-400 transition-transform`} />
                            )}
                        </button>

                        {/* Submenu */}
                        {!collapsed && item.submenu && expandedItem.has(item.id) && (
                            <div className="ml-8 mt-2 space-y-1">
                                { item.submenu.map((sub) => (<button className="w-full text-left p-2 text-sm text-slate-600 hover:bg-slate-100 rounded-lg">{sub.label}</button>))}
                            </div>
                        )}
                    </div>
                ))}
            </nav>

            {!collapsed && (
                <div className="p-4 border-t border-slate-200/50 ">
                <div className="flex items-center space-x-3 p-3 rounded-xl bg-slate-50">
                    <img
                        src="https://png.pngtree.com/png-vector/20191110/ourmid/pngtree-avatar-icon-profile-icon-member-login-vector-isolated-png-image_1978396.jpg"
                        alt="user"
                        className="w-10 h-10 rounded-full "
                    />
                    <div className="flex-1 min-w-0">
                        <div className="flex-1 min-w-0">
                            <p className="text-sm font-medium text-slate-800 truncate">
                                Name Surname
                            </p>
                            <p className="text-xs text-slate-500 truncate">
                                Role
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            )}
        </div>
    );
}

export default Sidebar;
