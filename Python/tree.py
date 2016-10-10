# generate a network diagram (tree)
# requires the PyGraphviz library

# draw graph linear order or not
linear = False
output = False

# import pygraphviz for tree structures
import pygraphviz as pg

# create empty graph
graph = pg.AGraph()

# add sets
graph.add_node('k', shape='square', color="#7979FF", style="filled")
graph.add_node('ma', shape='square', color="#79FF79", style="filled")
graph.add_node('md', shape='square', color="#7979BC", style="filled")
graph.add_node('pa', shape='square', color="#FFBC8F", style="filled")
graph.add_node('pd', shape='square', color="#DDDD69", style="filled")
graph.add_node('Xa', shape='square', color="#BC9479", style="filled")
graph.add_node('Xd', shape='square', color="#FF79FF", style="filled")

# add permutations
graph.add_node('k-')
graph.add_node('ma-')
graph.add_node('md-')
graph.add_node('pa-')
graph.add_node('pd-')
graph.add_node('Xa-')
graph.add_node('Xd-')
graph.add_node('k-pa-pd')
graph.add_node('pa-pd')
graph.add_node('k-ma-md-pa-pd')
graph.add_node('md-pd')
graph.add_node('ma-pd')
graph.add_node('ma-pa-pd')
graph.add_node('k-ma-md')
graph.add_node('ma-md')
graph.add_node('md-pa')
graph.add_node('Xa-Xd')
graph.add_node('Xa-Xd-k')
graph.add_node('k-ma')
graph.add_node('k-pa')
graph.add_node('ma-pa')

# add connections from sets to permutations
graph.add_edge('k', 'k-', color="#7979FF")
graph.add_edge('k', 'k-pa-pd', color="#7979FF")
graph.add_edge('k', 'k-ma-md-pa-pd', color="#7979FF")
graph.add_edge('k', 'k-ma-md', color="#7979FF")
graph.add_edge('k', 'Xa-Xd-k', color="#7979FF")
graph.add_edge('k', 'k-ma', color="#7979FF")
graph.add_edge('ma', 'ma-', color="#79FF79")
graph.add_edge('ma', 'k-ma-md-pa-pd', color="#79FF79")
graph.add_edge('ma', 'k-ma-md', color="#79FF79")
graph.add_edge('ma', 'ma-pd', color="#79FF79")
graph.add_edge('ma', 'ma-md', color="#79FF79")
graph.add_edge('ma', 'ma-pa', color="#79FF79")
graph.add_edge('ma', 'k-ma', color="#79FF79")
graph.add_edge('md', 'md-', color="#7979BC")
graph.add_edge('md', 'k-ma-md-pa-pd', color="#7979BC")
graph.add_edge('md', 'md-pd', color="#7979BC")
graph.add_edge('md', 'k-ma-md', color="#7979BC")
graph.add_edge('md', 'ma-md', color="#7979BC")
graph.add_edge('md', 'md-pa', color="#7979BC")
graph.add_edge('pa', 'pa-', color="#FFBC8F")
graph.add_edge('pa', 'k-pa-pd', color="#FFBC8F")
graph.add_edge('pa', 'k-ma-md-pa-pd', color="#FFBC8F")
graph.add_edge('pa', 'k-pa-pd', color="#FFBC8F")
graph.add_edge('pa', 'pa-pd', color="#FFBC8F")
graph.add_edge('pa', 'ma-pa-pd', color="#FFBC8F")
graph.add_edge('pa', 'md-pa', color="#FFBC8F")
graph.add_edge('pa', 'k-pa', color="#FFBC8F")
graph.add_edge('pa', 'ma-pa', color="#FFBC8F")
graph.add_edge('pd', 'pd-', color="#DDDD69")
graph.add_edge('pd', 'k-pa-pd', color="#DDDD69")
graph.add_edge('pd', 'pa-pd', color="#DDDD69")
graph.add_edge('pd', 'k-ma-md-pa-pd', color="#DDDD69")
graph.add_edge('pd', 'md-pd', color="#DDDD69")
graph.add_edge('pd', 'ma-pd', color="#DDDD69")
graph.add_edge('pd', 'ma-pa-pd', color="#DDDD69")
graph.add_edge('Xa', 'Xa-', color="#BC9479")
graph.add_edge('Xa', 'Xa-Xd', color="#BC9479")
graph.add_edge('Xa', 'Xa-Xd-k', color="#BC9479")
graph.add_edge('Xd', 'Xd-', color="#FF79FF")
graph.add_edge('Xd', 'Xa-Xd', color="#FF79FF")
graph.add_edge('Xd', 'Xa-Xd-k', color="#FF79FF")

# graph attributes
graph.graph_attr['label'] = "Random Sets"
graph.node_attr['shape'] = "circle"
graph.edge_attr['len'] = "1.5"

# draw graph
if(linear):
  graph.layout(prog="dot")
else:
  graph.layout()
graph.draw('tree.png')

# spit out string version of graph
if(output):
  print(graph.string())
