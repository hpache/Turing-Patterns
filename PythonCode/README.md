# Python Code

This directory contains all the python code used to numerically simulate reaction-diffusion systems. Since all the code
is on jupyter notebooks, you might need to [install jupyter notebook](https://jupyter.org/install) onto your system.

Apart from Jupyter notebooks, there are many dependencies that need to be installed in order to properly run the notebooks.

1. Numpy
2. Scipy
3. Matplotlib
4. Py-pde

In order to instally these libraries the user only needs to run the following command on their computer

```
pip install numpy scipy matplotlib py-pde
```

If you are on a mac and get errors when trying to install py-pde, I found that running the code on an anaconda distribution of python helps. [Miniconda](https://docs.conda.io/en/latest/miniconda.html) is the smallest version of anaconda, and it won't install extra managers for you (although they can be useful). If you need to use minconda, the following command will install the required dependencies for you

```
conda install numpy scipy matplotlib py-pde
```

Once all the dependencies are installed, you can simply open the notebooks on vscode, or start your Jupyter local server with the following command

```
jupyter notebook
```

Make sure that you are in the PythonCode directory before running this command (cd into the directory from your terminal) !!


# References

Models were taken from the following papers:

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 TRANSITIONAL//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="htmlExportStyleSheet.css" />
</head>

<body>
<div class="content">
<dl>
<dd class="Pub">
	<span class="Title">Bifurcation and Turing patterns of reaction--diffusion activator--inhibitor model</span><br />
	<span class="Author">R. Wu, Y. Zhou, Y. Shao, and L. Chen</span><br />
	<span class="Journal">Physica A: Statistical Mechanics and its Applications</span>&nbsp;
	<span class="Volume">482</span>&nbsp;
	<span class="Pages">597-610</span>&nbsp;
	(<span class="Date">2017</span>)<br />
	<span class="URL"><a href="https://www.sciencedirect.com/science/article/pii/S0378437117303576">https://www.sciencedirect.com/science/article/pii/S0378437117303576</a></span><br />
	<span class="URL"><a href="https://doi.org/10.1016/j.physa.2017.04.053">https://doi.org/10.1016/j.physa.2017.04.053</a></span><br />
	
</dd>

</dl>
</div>

<div class="content">

<dl>
<dd class="Pub">
	<span class="Title">Cellular automata for reaction-diffusion systems</span><br />
	<span class="Author">J. R. Weimar</span><br />
	<span class="Journal">Parallel Computing</span>&nbsp;
	<span class="Volume">23</span>&nbsp;
	<span class="Pages">1699-1715</span>&nbsp;
	(<span class="Date">1997</span>)<br />
	<span class="URL"><a href="https://www.sciencedirect.com/science/article/pii/S0167819197000811">https://www.sciencedirect.com/science/article/pii/S0167819197000811</a></span><br />
	<span class="URL"><a href="https://doi.org/10.1016/S0167-8191(97)00081-1">https://doi.org/10.1016/S0167-8191(97)00081-1</a></span><br />
	
</dd>

</dl>
</div>



<div class="content">
<dl>
<dd class="Pub">
	<span class="Title">Pattern formation in the Brusselator system</span><br />
	<span class="Author">R. Peng and M. Wang</span><br />
	<span class="Journal">Journal of Mathematical Analysis and Applications</span>&nbsp;
	<span class="Volume">309</span>&nbsp;
	<span class="Pages">151-166</span>&nbsp;
	(<span class="Date">2005</span>)<br />
	<span class="URL"><a href="https://www.sciencedirect.com/science/article/pii/S0022247X04010546">https://www.sciencedirect.com/science/article/pii/S0022247X04010546</a></span><br />
	<span class="URL"><a href="https://doi.org/10.1016/j.jmaa.2004.12.026">https://doi.org/10.1016/j.jmaa.2004.12.026</a></span><br />
</dd>

</dl>
</div>
</body>
</html>
