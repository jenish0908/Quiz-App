import React, { useState, useRef } from "react";

interface NavbarProps {
  username?: string | null;
  onProfileClick: () => void;
  onLogout: () => void;
  onSearch: (query: string) => void;
}

const Navbar: React.FC<NavbarProps> = ({ username, onProfileClick, onLogout, onSearch }) => {
  const [searchValue, setSearchValue] = useState("");
  const debounceTimeout = useRef<NodeJS.Timeout | null>(null);

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setSearchValue(value);

    // Clear previous timeout
    if (debounceTimeout.current) clearTimeout(debounceTimeout.current);

    // Set new timeout
    debounceTimeout.current = setTimeout(() => {
      onSearch(value); // Call backend search after 500ms
    }, 500);
  };

  return (
    <nav style={{
      display: "flex",
      justifyContent: "space-between",
      alignItems: "center",
      padding: "15px 30px",
      backgroundColor: "#1e1e1e",
      color: "#fff",
      boxShadow: "0 2px 5px rgba(0,0,0,0.2)"
    }}>
      <h2 style={{ margin: 0 }}>Quiz Platform</h2>

      <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
        {/* Search bar */}
        <input
          type="text"
          placeholder="Search questions..."
          value={searchValue}
          onChange={handleSearchChange}
          style={{
            padding: "6px 12px",
            borderRadius: "5px",
            border: "none",
            outline: "none",
            fontSize: "14px"
          }}
        />

        {username && <span>{username}</span>}

        <div 
          onClick={onProfileClick} 
          style={{
            width: "45px",
            height: "45px",
            borderRadius: "50%",
            backgroundColor: "#4caf50",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            cursor: "pointer",
            fontWeight: "bold",
            fontSize: "18px",
            color: "#fff"
          }}
          title="Profile"
        >
          {username ? username.charAt(0).toUpperCase() : "U"}
        </div>

        <button onClick={onLogout} style={{
          padding: "6px 14px",
          cursor: "pointer",
          borderRadius: "5px",
          border: "none",
          backgroundColor: "#f44336",
          color: "#fff",
          fontWeight: "bold"
        }}>Logout</button>
      </div>
    </nav>
  );
};

export default Navbar;
