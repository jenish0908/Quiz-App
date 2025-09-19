// src/api/apiClient.ts
interface RequestOptions {
  url: string;
  method: "GET" | "POST" | "PUT" | "DELETE";
  body?: any;
  params?: Record<string, string>;
}

export async function apiRequest({ url, method, body, params }: RequestOptions) {
  let finalUrl = url;
  if (method === "GET" && params) {
    const queryString = new URLSearchParams(params).toString();
    finalUrl = `${url}?${queryString}`;
  }

  const token = localStorage.getItem("token");
  const headers: Record<string, string> = { "Content-Type": "application/json" };
  if (token) headers["Authorization"] = `Bearer ${token}`;

  const response = await fetch(finalUrl, {
    method,
    headers,
    body: method === "POST" || method === "PUT" ? JSON.stringify(body) : undefined,
  });

  const text = await response.text();
  try {
    const json = JSON.parse(text);
    if (!response.ok) throw new Error(json.message || text);
    return json;
  } catch (e) {
    if (!response.ok) throw new Error(text || "API Error");
    return text;
  }
}
