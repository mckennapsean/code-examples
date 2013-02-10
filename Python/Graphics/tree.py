# by Sean McKenna
# generate a network diagram (tree)
# requires the PyGraphviz library

# draw graph linear order or not
linear = False
output = False

# import pygraphviz for tree structures
import pygraphviz as pg

# create empty graph
g = pg.AGraph()

# add sets
g.add_node('k', shape='square', color="#7979FF", style="filled")
g.add_node('ma', shape='square', color="#79FF79", style="filled")
g.add_node('md', shape='square', color="#7979BC", style="filled")
g.add_node('pa', shape='square', color="#FFBC8F", style="filled")
g.add_node('pd', shape='square', color="#DDDD69", style="filled")
g.add_node('Xa', shape='square', color="#BC9479", style="filled")
g.add_node('Xd', shape='square', color="#FF79FF", style="filled")

# add permutations
g.add_node('k-')
g.add_node('ma-')
g.add_node('md-')
g.add_node('pa-')
g.add_node('pd-')
g.add_node('Xa-')
g.add_node('Xd-')
g.add_node('k-pa-pd')
g.add_node('pa-pd')
g.add_node('k-ma-md-pa-pd')
g.add_node('md-pd')
g.add_node('ma-pd')
g.add_node('ma-pa-pd')
g.add_node('k-ma-md')
g.add_node('ma-md')
g.add_node('md-pa')
g.add_node('Xa-Xd')
g.add_node('Xa-Xd-k')
g.add_node('k-ma')
g.add_node('k-pa')
g.add_node('ma-pa')

# add connections from sets to permutations
g.add_edge('k', 'k-', color="#7979FF")
g.add_edge('k', 'k-pa-pd', color="#7979FF")
g.add_edge('k', 'k-ma-md-pa-pd', color="#7979FF")
g.add_edge('k', 'k-ma-md', color="#7979FF")
g.add_edge('k', 'Xa-Xd-k', color="#7979FF")
g.add_edge('k', 'k-ma', color="#7979FF")
g.add_edge('ma', 'ma-', color="#79FF79")
g.add_edge('ma', 'k-ma-md-pa-pd', color="#79FF79")
g.add_edge('ma', 'k-ma-md', color="#79FF79")
g.add_edge('ma', 'ma-pd', color="#79FF79")
g.add_edge('ma', 'ma-md', color="#79FF79")
g.add_edge('ma', 'ma-pa', color="#79FF79")
g.add_edge('ma', 'k-ma', color="#79FF79")
g.add_edge('md', 'md-', color="#7979BC")
g.add_edge('md', 'k-ma-md-pa-pd', color="#7979BC")
g.add_edge('md', 'md-pd', color="#7979BC")
g.add_edge('md', 'k-ma-md', color="#7979BC")
g.add_edge('md', 'ma-md', color="#7979BC")
g.add_edge('md', 'md-pa', color="#7979BC")
g.add_edge('pa', 'pa-', color="#FFBC8F")
g.add_edge('pa', 'k-pa-pd', color="#FFBC8F")
g.add_edge('pa', 'k-ma-md-pa-pd', color="#FFBC8F")
g.add_edge('pa', 'k-pa-pd', color="#FFBC8F")
g.add_edge('pa', 'pa-pd', color="#FFBC8F")
g.add_edge('pa', 'ma-pa-pd', color="#FFBC8F")
g.add_edge('pa', 'md-pa', color="#FFBC8F")
g.add_edge('pa', 'k-pa', color="#FFBC8F")
g.add_edge('pa', 'ma-pa', color="#FFBC8F")
g.add_edge('pd', 'pd-', color="#DDDD69")
g.add_edge('pd', 'k-pa-pd', color="#DDDD69")
g.add_edge('pd', 'pa-pd', color="#DDDD69")
g.add_edge('pd', 'k-ma-md-pa-pd', color="#DDDD69")
g.add_edge('pd', 'md-pd', color="#DDDD69")
g.add_edge('pd', 'ma-pd', color="#DDDD69")
g.add_edge('pd', 'ma-pa-pd', color="#DDDD69")
g.add_edge('Xa', 'Xa-', color="#BC9479")
g.add_edge('Xa', 'Xa-Xd', color="#BC9479")
g.add_edge('Xa', 'Xa-Xd-k', color="#BC9479")
g.add_edge('Xd', 'Xd-', color="#FF79FF")
g.add_edge('Xd', 'Xa-Xd', color="#FF79FF")
g.add_edge('Xd', 'Xa-Xd-k', color="#FF79FF")

# graph attributes
g.graph_attr['label'] = "Random Sets"
g.node_attr['shape'] = "circle"
g.edge_attr['len'] = "1.5"

# draw graph
if(linear):
  g.layout(prog="dot")
else:
  g.layout()
g.draw('tree.png')

# spit out string version of graph
if(output):
  print g.string()
