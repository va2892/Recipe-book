import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { getProducts } from "../api/auth";
import axios from "axios";

export default function CreateDishPage() {
  const [products, setProducts] = useState([]);
  const [items, setItems] = useState([]);

  const [name, setName] = useState("");
  const [portionSize, setPortionSize] = useState("");
  const [category, setСategory] = useState("");

  useEffect(() => {
    getProducts().then(res => setProducts(res.data));
  }, []);

  const addItem = (productId) => {
    setItems([...items, { productId, amount: 100 }]);
  };

  const submit = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/dishes", {
        name,
        portionSize,
        category,
        items
      });

      console.log("Создано блюдо:", response.data);
      alert("Блюдо создано!");
    } catch (error) {
      console.error(error);
      alert("Ошибка при создании продукта");
    }
  };

  return (
    <div>
      <nav className="nav">
        <Link to="/">Продукты</Link>
        <Link to="/create-product">Создать продукт</Link>
        <Link to="/create-dish">Создать блюдо</Link>
      </nav>

      <h1>Создать блюдо</h1>

      <p>Название блюда:</p>
      <input
        placeholder="Название"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <br/><br/>
      <p>Размер порции:</p>
      <input
        placeholder="Размер порции"
        value={portionSize}
        onChange={e => setPortionSize(e.target.value)}
      />
      <br/><br/>
      <p>Категория блюда:</p>
      <input
        placeholder="Категория"
        value={category}
        onChange={e => setСategory(e.target.value)}
      />

      <br/><br/>
      <h3>Продукты</h3>
      {products.map(p => (
        <div key={p.id}>
          {p.name}&nbsp; 
          <button onClick={() => addItem(p.id)}>Добавить продукт</button>
        </div>
      ))}

      <br/><br/>
      <h3>Выбранные продукты</h3>

      {items.map((item, index) => {
        const product = products.find(p => p.id === item.productId);

        return (
          <div key={index} style={{ marginBottom: "10px" }}>
            <strong>{product?.name}</strong>

            <br/>

            Количество (г):
            <input
              type="number"
              value={item.amount}
              onChange={(e) => {
                const newItems = [...items];
                newItems[index].amount = Number(e.target.value);
                setItems(newItems);
              }}
            />

            <button onClick={() => { setItems(items.filter((_, i) => i !== index)); }}>Удалить</button>
          </div>
        );
      })}

      <br/><br/>
      <button onClick={submit}>Создать блюдо</button>
    </div>
  );
}