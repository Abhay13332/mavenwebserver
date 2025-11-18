 document.addEventListener('DOMContentLoaded', (event) => {
    const createDivBtn = document.getElementById('createDivBtn');
    const container = document.getElementById('container');
    let count = 0;


    function createNewDiv() {
        count++;
        const newDiv = document.createElement('div');
        newDiv.classList.add('new-div');


        newDiv.innerHTML = `This is dynamically created div number ${count}. Click to remove.`;


        newDiv.addEventListener('click', function() {
            container.removeChild(newDiv);
        });

        container.appendChild(newDiv);
    }


    createDivBtn.addEventListener('click', createNewDiv);
});
