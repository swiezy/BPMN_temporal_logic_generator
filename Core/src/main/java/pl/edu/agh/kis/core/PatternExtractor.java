/*
 * Copyright (c) 2015, pl.edu.agh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package pl.edu.agh.kis.core;

import edu.uci.ics.jung.graph.Graph;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import pl.edu.agh.kis.core.data.Node;
import pl.edu.agh.kis.core.data.StructNode;
import pl.edu.agh.kis.exceptions.BadPatternException;
import pl.edu.agh.kis.patterns.Discriminator;
import pl.edu.agh.kis.patterns.ExclusiveChoice;
import pl.edu.agh.kis.patterns.ImplicitTermination;
import pl.edu.agh.kis.patterns.MultipleChoice;
import pl.edu.agh.kis.patterns.MultipleMerge;
import pl.edu.agh.kis.patterns.ParallelSplit;
import pl.edu.agh.kis.patterns.Pattern;
import pl.edu.agh.kis.patterns.Sequence;
import pl.edu.agh.kis.patterns.SimpleMerge;
import pl.edu.agh.kis.patterns.SynchronizingMerge;

/**
 *
 * @author Adam Świeżowski, Jakub Piotrowski
 * Class contains a list of priorities by which patterns have to be searched
 */
public class PatternExtractor {

    public final static List<Pattern> PATTERN_PRIORITY;

    /**
     * Initialization priority list patterns
     */
    static {
        PATTERN_PRIORITY = new ArrayList<>();
        PATTERN_PRIORITY.add(new SynchronizingMerge());
        PATTERN_PRIORITY.add(new Discriminator());
        PATTERN_PRIORITY.add(new MultipleMerge());
        PATTERN_PRIORITY.add(new ImplicitTermination());
        PATTERN_PRIORITY.add(new ExclusiveChoice());
        PATTERN_PRIORITY.add(new MultipleChoice());
        PATTERN_PRIORITY.add(new ParallelSplit());
        PATTERN_PRIORITY.add(new SimpleMerge());
        PATTERN_PRIORITY.add(new Sequence());
    }

    /**
     * Extracts the patterns of graph
     * @param g graph to extract
     * @param startNode graph start node, where algorithm begins search
     * @return the node where the exploration ends
     * @throws UnexpectedException
     * @throws BadPatternException 
     */
    public Node extractPatterns(Graph g, Node startNode) throws UnexpectedException, BadPatternException {

        StructNode patternTree = null;
        for (Pattern p : PATTERN_PRIORITY) {
            if (g.getOutEdges(startNode).isEmpty()) {
                return startNode;
            }
            patternTree = p.findPattern(g, startNode);

            if (patternTree != null) {
                g = Pattern.replaceGraphSegment(g, patternTree);
                startNode = patternTree;
            }
        }
        return patternTree;
    }

}
