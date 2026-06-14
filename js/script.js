// Base de datos simulada en el navegador
let inventory = [
    { id: 1, name: "Arroz Integral", stock: 3, price: 4500 },
    { id: 2, name: "Leche Entera", stock: 12, price: 3800 }
];
let nextId = 3;

// Función para actualizar la tabla y evaluar las alertas de la problemática
function updateDashboard() {
    const tbody = document.getElementById("inventoryTableBody");
    const alertContainer = document.getElementById("alertContainer");
    tbody.innerHTML = "";
    alertContainer.innerHTML = "";
    
    let alerts = [];

    inventory.forEach(product => {
        let statusBadge = "";
        
        // Lógica de alertas para resolver la problemática
        if (product.stock === 0) {
            statusBadge = '<span class="badge badge-critical">Out of Stock</span>';
            alerts.push(`Critical: ¡El producto "${product.name}" se ha agotado por completo! Pérdida de ventas potencial.`);
        } else if (product.stock <= 5) {
            statusBadge = '<span class="badge badge-warning">Low Stock</span>';
            alerts.push(`Warning: "${product.name}" tiene menos de 5 unidades (${product.stock} disp.). Reordenar pronto.`);
        } else {
            statusBadge = '<span class="badge badge-ok">In Stock</span>';
        }

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${String(product.id).padStart(3, '0')}</td>
            <td>${product.name}</td>
            <td>${product.stock} u.</td>
            <td>$${Number(product.price).toLocaleString()}</td>
            <td>${statusBadge}</td>
            <td><button class="btn btn-danger" style="padding: 5px 10px; font-size: 0.85em;" onclick="deleteProduct(${product.id})">Delete</button></td>
        `;
        tbody.appendChild(row);
    });

    // Mostrar alertas en la interfaz
    if (alerts.length === 0) {
        alertContainer.innerHTML = '<p class="no-alerts">✅ El inventario se encuentra en niveles óptimos.</p>';
    } else {
        alerts.forEach(alertText => {
            const p = document.createElement("p");
            p.className = "alert-item";
            p.innerText = alertText;
            alertContainer.appendChild(p);
        });
    }
}

// Función para agregar un producto
function addProduct() {
    const name = document.getElementById("prodName").value.trim();
    const stock = parseInt(document.getElementById("prodStock").value);
    const price = parseFloat(document.getElementById("prodPrice").value);

    if (!name || isNaN(stock) || isNaN(price)) {
        alert("Por favor, complete todos los campos correctamente.");
        return;
    }

    const newProduct = { id: nextId++, name, stock, price };
    inventory.push(newProduct);
    updateDashboard();
    document.getElementById("inventoryForm").reset();
}

// Función para eliminar un producto
function deleteProduct(id) {
    inventory = inventory.filter(p => p.id !== id);
    updateDashboard();
}

// Función para buscar un producto por su nombre
function searchProduct() {
    const searchName = document.getElementById("prodName").value.trim().toLowerCase();
    if (!searchName) {
        alert("Ingrese el nombre en el campo de registro para buscar.");
        return;
    }
    
    const found = inventory.filter(p => p.name.toLowerCase().includes(searchName));
    if (found.length > 0) {
        alert(`Producto encontrado:\n${found.map(p => `- ${p.name}: ${p.stock} unidades ($${p.price})`).join("\n")}`);
    } else {
        alert("No se encontraron productos con ese nombre.");
    }
}

// Inicializar al cargar la página
updateDashboard();
