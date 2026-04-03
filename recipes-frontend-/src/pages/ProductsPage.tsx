import { useState, useEffect } from 'react'
import { Link } from "react-router-dom";
import { getProducts } from "../api/auth";

export default function ProductsPage() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getProducts().then(res => setProducts(res.data));
  }, []);

  return (
    <div>
      <nav className="nav">
        <Link to="/">Продукты</Link>
        <Link to="/create-product">Создать продукт</Link>
        <Link to="/create-dish">Создать блюдо</Link>
      </nav>

      <h1>Продукты</h1>

      {products.map(p => {
        return (
          <div key={p.id}>
            {p.name} — {p.calories} ккал | БЖУ: {p.protein}, {p.fat}, {p.carbs}
          </div>
        );
      })}
    </div>
  );
}