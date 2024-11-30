
const API_BASE_URL = 'http://localhost:8085/api/admin';
async function populateCategoriesDropdown() {
    const categoriesMenu = document.getElementById('categories-menu');
    categoriesMenu.innerHTML = ''; // Clear existing items
    const categories = await fetchCategories();

    if (categories && Array.isArray(categories)) {
        categories.forEach(category => {
            console.log(category)
            const categoryItem = document.createElement('li');
            categoryItem.innerHTML = `
                <a href="/products?categoryId=${category.categoryId}">
                    ${category.name}
                </a>`;
            categoriesMenu.appendChild(categoryItem);
        });
    } else {
        console.error('Failed to load categories');
    }
}

// Function to fetch categories from the backend
async function fetchCategories() {
    try {
        const response = await fetch(`${API_BASE_URL}/categories`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const categories = await response.json();
        console.log('Categories:', categories); // Handle the categories data as needed
        return categories; // Return the fetched categories
    } catch (error) {
        console.error('Error fetching categories:', error);
        return null; // Return null in case of an error
    }
}

// Call the function when the page loads
document.addEventListener('DOMContentLoaded', populateCategoriesDropdown);
