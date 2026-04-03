import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

export default function CreateDishPage() {
	const [name, setName] = useState("");
  	const [calories, setCalories] = useState("");
  	const [protein, setProtein] = useState("");
  	const [fat, setFat] = useState("");
  	const [carbs, setCarbs] = useState("");
  	const [category, setCategory] = useState("Овощи");
  	const [cookingType, setCookingType] = useState("Готовый к употреблению");

  	const handleSubmit = async () => {
	    try {
	      	const product = {
	      	  	name,
	      	  	calories: parseFloat(calories),
	      	  	protein: parseFloat(protein),
	      	  	fat: parseFloat(fat),
	      	  	carbs: parseFloat(carbs),
	      	  	category,
	      	  	cookingType
	      	};
	
	      	const response = await axios.post(
	      	  	"http://localhost:8080/api/products",
	      	  	product
	      	);
	
	      	console.log("Создан продукт:", response.data);
	      	alert("Продукт создан!");
	
	      	setName("");
	      	setCalories("");
	      	setProtein("");
	      	setFat("");
	      	setCarbs("");
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

	     	<h1>Создать продукт</h1>
	     	<div>
		       <p>Название:</p>
		       <input
		         value={name}
		         onChange={(e) => setName(e.target.value)}
		       />
		     </div>
		     <div>
		       <p>Калории:</p>
		       <input
		         type="number"
		         value={calories}
		         onChange={(e) => setCalories(e.target.value)}
		       />
		     </div>
		     <div>
		       <p>Белки:</p>
		       <input
		         type="number"
		         value={protein}
		         onChange={(e) => setProtein(e.target.value)}
		       />
		     </div>
		     <div>
		       <p>Жиры:</p>
		       <input
		         type="number"
		         value={fat}
		         onChange={(e) => setFat(e.target.value)}
		       />
		     </div>
		     <div>
		       <p>Углеводы:</p>
		       <input
		         type="number"
		         value={carbs}
		         onChange={(e) => setCarbs(e.target.value)}
		       />
		     </div>
		     <div>
		       <p>Категория:</p>
		       <select value={category} onChange={(e) => setCategory(e.target.value)}>
		         <option>Замороженный</option>
		         <option>Мясной</option>
		         <option>Овощи</option>
		         <option>Зелень</option>
		         <option>Специи</option>
		         <option>Крупы</option>
		         <option>Консервы</option>
		         <option>Жидкость</option>
		         <option>Сладости</option>
		       </select>
		     </div>
		     <div>
		       <p>Тип готовки:</p>
		       <select
		         value={cookingType}
		         onChange={(e) => setCookingType(e.target.value)}
		       >
		         <option>Готовый к употреблению</option>
		         <option>Полуфабрикат</option>
		         <option>Требует приготовления</option>
		       </select>
		     </div>
		     <br />
		     <button onClick={handleSubmit}>Создать продукт</button>
	 	</div>
	);
}